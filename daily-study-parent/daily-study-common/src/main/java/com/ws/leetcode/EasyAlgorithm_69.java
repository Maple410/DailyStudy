package com.ws.leetcode;

/**
 * @Author: wangshuo
 * @Date: 2022/4/1 8:46
 * <p>
 * LeetCode -69 X 的平方根
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 */
public class EasyAlgorithm_69 {

    public int mySqrt(int x) {


        int length = String.valueOf(x).length();
        int sqrt = length % 2 == 0 ? length / 2 : length / 2 + 1;



        return 0;
    }
}
