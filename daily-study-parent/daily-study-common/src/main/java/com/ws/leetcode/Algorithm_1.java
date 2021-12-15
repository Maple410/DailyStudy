package com.ws.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: wangshuo
 * @Date: 2021/12/15 15:01
 * <p>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class Algorithm_1 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        List<Integer> num1List = new ArrayList<>();
        List<Integer> num2List = new ArrayList<>();

        ListNode nextNode1 = l1;
        while (nextNode1 != null) {
            num1List.add(nextNode1.val);
            nextNode1 = nextNode1.next;
        }
        ListNode nextNode2 = l2;
        while (nextNode2 != null) {
            num2List.add(nextNode2.val);
            nextNode2 = nextNode2.next;
        }

        int maxSize = num1List.size() >= num2List.size() ? num1List.size() : num2List.size();
        int minSize = num1List.size() >= num2List.size() ? num2List.size() : num1List.size();

        int[] resultArr = new int[maxSize + 1];
        Object[] shortArr = new Object[minSize];

        if (num1List.size() >= num2List.size()) {
            for (int i = 0; i < num1List.size(); i++) {
                resultArr[i] = num1List.get(i);
            }
            shortArr = num2List.toArray();
        } else {
            for (int i = 0; i < num1List.size(); i++) {
                resultArr[i] = num1List.get(i);
            }
            shortArr = num2List.toArray();
        }

        for (int i = 0; i < minSize; i++) {
            int tempNum = (int) shortArr[i] + resultArr[i];
            if (tempNum >= 10) {
                resultArr[i] = tempNum - 10;
                resultArr[i + 1] = resultArr[i + 1] + 1;
            } else {
                resultArr[i] = tempNum;
            }
        }


        ListNode resultNode = new ListNode(resultArr[0]);
        ListNode nextNode = null;
        nextNode = resultNode;

        for (int i = 1; i < resultArr.length - 1; i++) {
            ListNode node = new ListNode(resultArr[i]);
            nextNode.next = node;
            nextNode = nextNode.next;

        }
        if (resultArr[resultArr.length - 1] != 0) {
            ListNode node = new ListNode(resultArr[resultArr.length - 1]);
            nextNode.next = node;
            nextNode = nextNode.next;
        }

        return resultNode;
    }


    public static void main(String[] args) {

        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(4, l1);
        ListNode l3 = new ListNode(9, l2);


        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(6, l4);
        ListNode l6 = new ListNode(5, l5);

        ListNode resultNode = addTwoNumbers(l3, l6);

        while (resultNode != null) {
            System.out.println(resultNode.val);
            resultNode = resultNode.next;
        }


    }

}
