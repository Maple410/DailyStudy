package com.ws.utils;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/7/9 14:09
 */
public class SqlUtil {

    public static String getTableName(String sql, String dbType) {
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        SQLStatement sqlStatement = (SQLStatement) stmtList.get(0);
        SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) sqlStatement;
        SQLSelectQuery sqlSelectQuery = sqlSelectStatement.getSelect().getQuery();
        String tableName = null;
        if (sqlSelectQuery instanceof SQLSelectQueryBlock) {
            SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectQuery;
            SQLTableSource table = sqlSelectQueryBlock.getFrom();
            if (table instanceof SQLExprTableSource) {
                tableName = ((SQLExprTableSource) table).getName().getSimpleName();
            } else if (table instanceof SQLJoinTableSource) {
                SQLTableSource left = ((SQLJoinTableSource) table).getLeft();
                tableName = ((SQLExprTableSource) left).getName().getSimpleName();
            } else if (table instanceof SQLSubqueryTableSource) {
                tableName = table.getAlias();
            }
        }

        return tableName;
    }

}
