package com.ws.query;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 15:36
 */
public enum ConditionOperator {

    EQ("eq"),
    GT("gt"),
    GTE("gte"),
    IN("in"),
    NIN("nin"),
    LT("lt"),
    LTE("lte"),
    NE("ne"),
    BETWEEN("between"),
    LIKE("like");

    private String operator;

    ConditionOperator(String operator) {
        this.operator = operator;
    }}
