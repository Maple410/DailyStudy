package com.ws.interceptor;

import com.ws.utils.ParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

/**
 * @Author: wangshuo
 * @Date: 2021/7/9 14:01
 */

@Slf4j
@Intercepts(
        {
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
                @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
        }
)
public class UpdateMybatisInterceptor implements Interceptor {

    private static final String[] insertSupply = {"createUserId", "updateUserId"};
    private static final String[] updateSupply = {"updateUserId"};

    private static final String[] taskTable = {
            "company"
    };

    public UpdateMybatisInterceptor() {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();
        log.info("------sqlId------" + sqlId);
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        log.info("------sqlCommandType------" + sqlCommandType);
        if (parameter == null) {
            return invocation.proceed();
        }

        BoundSql sqlBound = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        String newSql = getSql(configuration, sqlBound, sqlId, 0);

        if (hasOfficeId(newSql, taskTable)) {
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                for (String str : insertSupply) {
                    constructField(parameter, str);
                }
            } else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                //删除 -> update 特殊处理一下
                if (!parameter.getClass().equals(Long.class)) {
                    MapperMethod.ParamMap mapperMethod = (MapperMethod.ParamMap) invocation.getArgs()[1];
                    for (String str : updateSupply) {
                        constructField(mapperMethod.get("param1"), str);
                    }
                }

            }
        }
        return invocation.proceed();
    }

    private void constructField(Object parameter, String str) {
        Field field = ReflectionUtils.findField(parameter.getClass(), str);
        if (null != field) {
            field.setAccessible(true);
            Object object = ReflectionUtils.getField(field, parameter);
            if (null == object) {
                ReflectionUtils.setField(field, parameter, ParamUtil.getUnionId());
            }
        }
    }


    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    private static String getSql(Configuration configuration, BoundSql boundSql,
                                 String sqlId, long time) {
        String sql = showSql(configuration, boundSql);
        StringBuilder str = new StringBuilder(100);
        str.append(sqlId);
        str.append(":");
        str.append(sql);
        return str.toString();
    }

    private static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (!parameterMappings.isEmpty() && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration
                    .getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?",
                        Matcher.quoteReplacement(getParameterValue(parameterObject)));

            } else {
                MetaObject metaObject = configuration
                        .newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql
                                .getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else {
                        sql = sql.replaceFirst("\\?", "缺失");
                    }//打印出缺失，提醒该参数缺失并防止错位
                }
            }
        }
        return sql;
    }

    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(
                    DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    private boolean hasOfficeId(String sql, String[] officeIdNames) {
        //office ID 的可能名称
        if (sql == null || sql.trim().length() == 0) {
            return false;
        }

        //String afterWhereStatement = sql.toUpperCase().substring(sql.indexOf("values"));
        String afterWhereStatement = sql;

        for (String officeIdName : officeIdNames) {
            if (afterWhereStatement.indexOf(officeIdName) > 0) {
                return true;
            }
        }

        return false;
    }
}
