package com.ws.leetcode.submitted;

/**
 * @Author: wangshuo
 * @Date: 2022/4/1 8:46
 * <p>
 * LeetCode -69 X 的平方根
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 */
public class EasyAlgorithm_69 {

    /**
     * 返回 x 的 算术平方根 。
     * @param x
     * @return
     * 该函数使用了二分查找的方法来计算算术平方根。
     * 首先，如果输入的x为0或1，则直接返回x本身。
     * 然后，定义左右指针分别为1和x，然后在循环中不断缩小左右指针的范围，
     * 直到找到最接近x的算术平方根为止。在每一次循环中，先计算出中间值mid，
     * 然后根据mid与x/mid的大小关系来判断mid是否为x的算术平方根。如果是，
     * 则将res更新为mid，并将left指针移动到mid+1的位置；否则，将right指针移动到mid-1的位置。最终返回res即可。
     */
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 1, right = x, res=0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(69));
    }
}
