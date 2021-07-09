package com.ws.interceptor;

import com.alibaba.druid.util.JdbcConstants;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.ws.utils.ParamUtil;
import com.ws.utils.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;

/**
 * @Author: wangshuo
 * @Date: 2021/7/9 14:06
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SelectMybatisInterceptor implements Interceptor {

    public SelectMybatisInterceptor() {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType()
                || StatementType.CALLABLE == mappedStatement.getStatementType()) {
            return invocation.proceed();
        }

        //获取sql
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        String tableName = SqlUtil.getTableName(sql, JdbcConstants.MYSQL);
        //log.info("SelectMybatisInterceptor -> LoginUser:{}", ParamUtil.getUnionId());
        String newSql = sql;
        log.info("New SQL:{}", newSql);
        metaObject.setValue("delegate.boundSql.sql", newSql);


        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
