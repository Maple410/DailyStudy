package com.ws.leetcode.submitted;

import java.util.Stack;

/**
 * @Author: wangshuo
 * @Date: 2022/3/9 8:58
 * <p>
 * LeetCode-14 最长公共前缀（已提交）
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class EasyAlgorithm_14 {


    public static void main(String[] args) {
        String[] strArr = {""};
        System.out.println(commonSubstring(strArr));
    }


    public static String commonSubstring(String[] strs) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < strs[0].length(); i++) {
            char temp = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < i + 1) {
                    flag = true;
                    break;
                }
                if (temp != strs[j].charAt(i)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
            sb.append(temp);
        }
        return sb.toString();
    }

   /* public static String commonSubstring(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; ; i++) {
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].length() < i + 1) {
                    return sb.toString();
                }
                if (i + 1 >strs[j].length()) {
                    return sb.toString();
                }
                if (strs[j].charAt(i) != strs[j + 1].charAt(i)) {
                    return sb.toString();
                }
            }
            if (strs[0].length() == i) {
                return sb.toString();
            }
            sb.append(strs[0].charAt(i));
        }
    }*/


}
