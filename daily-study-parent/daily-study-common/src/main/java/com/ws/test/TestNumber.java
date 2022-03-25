package com.ws.test;

import java.math.BigDecimal;

/**
 * @Author: wangshuo
 * @Date: 2022/3/17 13:45
 */
public class TestNumber {
    public static void main(String[] args) {

        Double score = 0d;

        score = BigDecimal.valueOf(score).add(BigDecimal.valueOf(30 * 0.1).divide(BigDecimal.valueOf(100), 1, BigDecimal.ROUND_HALF_UP)).doubleValue();

        System.out.println(score);

    }
}
