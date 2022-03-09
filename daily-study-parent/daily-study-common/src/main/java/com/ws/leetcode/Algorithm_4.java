package com.ws.leetcode;

/**
 * @Author: wangshuo
 * @Date: 2022/3/8 9:04
 *
 * LeetCode -1 两数之和
 * <p>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class Algorithm_4 {


    public static void main(String[] args) {

        int[] num = {3,3};
        int target = 6;

        int[] resultArr = getTargetNum(num, target);
        System.out.println("数组下标为：" + resultArr[0] + "," + resultArr[1]);
    }

    public static int[] getTargetNum(int[] num, int target) {

        int[] resultArr = new int[2];
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] + num[j] == target) {
                    resultArr[0] = i;
                    resultArr[1] = j;
                    break;
                }
            }
        }
        return resultArr;
    }


}
