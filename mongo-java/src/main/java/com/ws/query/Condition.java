package com.ws.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 15:34
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condition {

    private String field;

    private ConditionOperator operator;

    private Object value;

    private Object[] array;

    private Object secondValue;
}
