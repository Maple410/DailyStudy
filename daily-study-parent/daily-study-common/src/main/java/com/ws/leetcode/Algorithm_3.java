package com.ws.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/12/24 11:06
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 */
public class Algorithm_3 {

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> resultList = new ArrayList<>();

        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length - 2; j++) {
                for (int t = j + 1; t < length - 1; t++) {
                    for (int f = t + 1; f < length; f++) {
                        if (nums[i] + nums[j] + nums[t] + nums[f] == target) {
                            List<Integer> tempList = new ArrayList<>(4);
                            tempList.add(nums[i]);
                            tempList.add(nums[j]);
                            tempList.add(nums[t]);
                            tempList.add(nums[f]);
                            boolean flag = true;
                            for (List<Integer> temp : resultList) {
                                if (temp.containsAll(tempList) && tempList.containsAll(temp)) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                resultList.add(tempList);
                            }
                        }
                    }
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        //int[] nums = {13, 10, 4, 5, 6, 7, 8, 2, 4, 6, 6, 3, 14, 4, 5, 6, 7, 8, 3, 30, 0, 0, 5, 6, 8, 9, 8, 0};
        int[] nums = {-2, -1, -1, 1, 1, 2};
        //int[] nums = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        int target = 0;
        System.out.println(fourSum(nums, target));


        List<Integer> num1List = new ArrayList<>();
        num1List.add(-2);
        num1List.add(-1);
        num1List.add(-1);
        num1List.add(1);
        System.out.println(num1List.get(3));

        List<Integer> num2List = new ArrayList<>();
        num2List.add(-1);
        num2List.add(-1);
        num2List.add(1);
        num2List.add(1);

        System.out.println(num1List.containsAll(num2List));

    }
}
