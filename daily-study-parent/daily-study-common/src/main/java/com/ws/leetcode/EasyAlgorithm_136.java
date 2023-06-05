package com.ws.leetcode;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author: wangshuo
 * @Date: 2023/6/5 17:20
 *
 *  LeetCode-136 只出现一次的数字
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 示例 1 ：
 *
 * 输入：nums = [2,2,1]
 * 输出：1
 * 示例 2 ：
 *
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 * 示例 3 ：
 *
 * 输入：nums = [1]
 * 输出：1
 *
 *
 *  看了 网上的解答 真的是 一个佩服了得
 *  交换律：a ^ b ^ c <=> a ^ c ^ b
 *  任何数于0异或为任何数 0 ^ n => n
 *  相同的数异或为0: n ^ n => 0
 */
public class EasyAlgorithm_136 {

    public static int singleNumber(int[] nums) {
        return  Arrays.stream(nums).reduce((a,b)->a^b).getAsInt();
    }

    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }
}
