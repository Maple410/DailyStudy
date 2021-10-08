package com.ws.log.record;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @Author: wangshuo
 * @Date: 2021/9/28 10:17
 */
public class TestSpEl {

    public static void main(String[] args) {

        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#root.name");

        Order order = new Order();
        order.setName("乐高");
        System.out.println(expression.getValue(order));


    }
}
