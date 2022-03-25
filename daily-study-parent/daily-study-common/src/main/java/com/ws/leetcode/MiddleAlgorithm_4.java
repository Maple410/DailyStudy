package com.ws.leetcode;

/**
 * @Author: wangshuo
 * @Date: 2022/3/11 8:50
 * <p>
 * * LeetCode=6 Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 */
public class MiddleAlgorithm_4 {

    public static String convert(String s, int numRows) {

        int elementCount = numRows * 2 - 2;
        int length = s.length();
        if (length <= numRows) {
            return s;
        }
        int el = length % elementCount;
        int weight = (length / elementCount) * (numRows - 1) + (el <= numRows-1 ? 1 : el - numRows + 1);

        char[][] chars = new char[numRows][weight];

        for (int i = 0; i < length; i++) {
            int row = 0;
            int t = i % elementCount;
            if (t < numRows) {
                row = t;
            } else {
                row = 2 * numRows - t - 2;
            }


            int column = 0;

            if (t < numRows) {
                column = i / elementCount * (numRows - 1);
            } else {
                column = i % elementCount - 3 + i / elementCount * (numRows - 1);
            }
            chars[row][column] = s.charAt(i);

        }

        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < weight; j++) {
                stringBuilder.append(chars[i][j]);
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING",3));
    }
}
