package com.ws.leetcode;

/**
 * @Author: wangshuo
 * @Date: 2022/3/26 15:41
 * <p>
 * LeetCode-28  实现 strStr() 函数。
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 * <p>
 *  
 * <p>
 * 说明：
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：haystack = "", needle = ""
 * 输出：0
 */
public class EasyAlgorithm_28 {


    public static void main(String[] args) {

        String haystack = "mississippi";
        String needle = "issip";
        System.out.println(strIn(haystack, needle));
    }


    public static int strIn(String haystack, String needle) {

        int needleLength = needle.length();
        if (needleLength == 0) {
            return 0;
        }
        int index = -1;
        char[] chars = haystack.toCharArray();
        int compareIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            if (needle.charAt(compareIndex) == chars[i]) {
                if (needleLength == compareIndex + 1) {
                    index = i - needleLength + 1;
                    break;
                }
                compareIndex++;
            } else {
                if(compareIndex>0){
                    i = i-compareIndex;
                }
                compareIndex = 0;
                index = -1;
            }
        }
        return index;
    }


}
