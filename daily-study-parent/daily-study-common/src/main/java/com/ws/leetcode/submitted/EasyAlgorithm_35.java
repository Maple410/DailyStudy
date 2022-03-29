package com.ws.leetcode.submitted;

/**
 * @Author: wangshuo
 * @Date: 2022/3/26 15:42
 * LeetCode-35 搜索插入位置 （已提交）
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 */
public class EasyAlgorithm_35 {


    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 7;
        System.out.println(searchInsertIndex(nums, target));
    }


    public static int searchInsertIndex(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;
        int middle = (high + low) / 2;
        if (target <= nums[low]) {
            return low;
        }
        if (target == nums[high]) {
            return high;
        }
        if (target > nums[high]) {
            return high + 1;
        }

        for (; ; ) {
            if (nums[middle] == target) {
                return middle;
            }
            middle = (high + low) / 2;
            if (nums[middle] > target) {
                high = middle;
            } else {
                low = middle;
            }

            if (high - low == 1) {
                if (nums[high] == target) {
                    return high;
                } else if (nums[low] == target) {
                    return low;
                } else {
                    return low + 1;
                }
            }
        }
    }
}
