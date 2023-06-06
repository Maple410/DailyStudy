package com.ws.leetcode.submitted;

/**
 * @Author: wangshuo
 * @Date: 2022/4/1 8:45
 * LeetCode - 67 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class EasyAlgorithm_67 {

    /**
     * 先将a 和 b 转化成十进制数，求和后再转化为二进制数
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {

        return Integer.toBinaryString(Integer.parseInt(a,2)+ Integer.parseInt(b,2));
    }

    /**
     * 借鉴了官网的做法
     * @param a
     * @param b
     * @return
     */
    public String addBinary1(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }



}
