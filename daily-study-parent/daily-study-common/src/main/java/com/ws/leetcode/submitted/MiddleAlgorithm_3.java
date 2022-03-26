package com.ws.leetcode.submitted;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/12/16 11:11
 * LeetCode-3 无重复字符的最长子串 （已提交）
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class MiddleAlgorithm_3 {


    /**
     * 解法1- 暴力解法
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring_1(String s) {

        int maxLength = 0;
        List<Character> characters = new ArrayList<>();

        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (characters.contains(charArr[i])) {
                if (maxLength < characters.size()) {
                    maxLength = characters.size();
                }
                int index = characters.indexOf(charArr[i]);
                characters = characters.subList(index + 1, characters.size());
            }
            characters.add(charArr[i]);
            if (maxLength < characters.size()) {
                maxLength = characters.size();
            }
        }
        if (maxLength == 0) {
            maxLength = characters.size();
        }
        return maxLength;
    }


    /**
     * 解法2-网上解法
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring_2(String s) {

      /*  HashMap<Character, Integer> lasts = new HashMap<>();
        int length = s.length();
        int result = 0;
        int start = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int lastIndex = lasts.containsKey(c) ? lasts.get(c) : -1;
            start = Math.max(start, lastIndex + 1);
            result = Math.max(result, i + 1 - start);
            lasts.put(c, i);
        }
        return result;*/

        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;

    }


    public static void main(String[] args) {


        System.out.println(lengthOfLongestSubstring_1("aabaab!bb"));
    }
}
