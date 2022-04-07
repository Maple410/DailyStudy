package com.ws.leetcode.submitted;

/**
 * @Author: wangshuo
 * @Date: 2022/3/26 15:43
 * LeetCode-53 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组 是数组中的一个连续部分。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 */
public class EasyAlgorithm_53 {


    public static void main(String[] args) {
        int[] nums = {5,4,-1,7,8};

        System.out.println(maxSubArrSum(nums));
    }

    public static int maxSubArrSum(int[] nums) {
        int max = nums[0];
        int tempMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = max < 0 ? nums[i] : nums[i] + max;
            tempMax = max > tempMax ? max : tempMax;
        }
        return tempMax;

    }


}
