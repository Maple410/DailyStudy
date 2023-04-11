package com.ws.leetcode.submitted;

/**
 * @Author: wangshuo
 * @Date: 2022/4/1 8:44
 *
 * LeetCode -66 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。

 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 */
public class EasyAlgorithm_66 {

    /**
     * 自己解法
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        digits[length-1] = digits[length-1] +1;
        int plusFlag = 0;
        for(int i=length-1;i>=0;i--){
            if(digits[i]==10){
                digits[i]=0;
                if(i==0){
                    plusFlag =1;
                }else{
                    digits[i-1] = digits[i-1] +1;
                }
            }
        }
        if(plusFlag ==0){
            return digits;
        }else{
            int[] result = new int[length+1];
            result[0]=1;
            return result;
        }
    }

    /**
     * 网上解题思路 简单清晰
     * @param digits
     * @return
     */
    public int[] plusOne1(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

}
