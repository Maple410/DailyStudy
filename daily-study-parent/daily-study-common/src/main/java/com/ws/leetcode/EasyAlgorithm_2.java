package com.ws.leetcode;

/**
 * @Author: wangshuo
 * @Date: 2022/3/8 9:05
 *
 * LeetCode-9 回文数 （已提交）
 * <p>
 * <p>
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 例如，121 是回文，而 123 不是。
 * <p>
 * 输入：x = 121
 * 输出：true
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 */
public class EasyAlgorithm_2 {


    public static void main(String[] args) {
        System.out.println(isPalindrome(10));
    }

    public static boolean isPalindrome(int n) {
        if (n < 0) {
            return false;
        }
        char[] numChar = String.valueOf(n).toCharArray();
        for (int i = 0; i < numChar.length; i++) {
            if (i > numChar.length / 2) {
                break;
            }
            if (numChar[i] != numChar[numChar.length - i - 1]) {
                return false;
            }

        }
        return true;
    }
}
