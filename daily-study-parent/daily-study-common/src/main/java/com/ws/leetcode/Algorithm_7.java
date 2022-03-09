package com.ws.leetcode;

/**
 * @Author: wangshuo
 * @Date: 2022/3/9 8:58
 * <p>
 * LeetCode-14 最长公共前缀
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
public class Algorithm_7 {


    public static void main(String[] args) {
        String[] strArr = {"what is your","what is who is you","what is are you" };
        System.out.println(commonSubstring(strArr));
    }


    public static String commonSubstring(String[] strArr) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0;; i++) {
            char temp = strArr[0].charAt(i);
            for (String str : strArr) {
                if (str.length() < i + 1) {
                    return sb.toString();
                }
                if (temp != str.charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(temp);
        }

    }


}
