package com.ws.leetcode;

import java.util.Stack;

/**
 * @Author: wangshuo
 * @Date: 2022/3/10 8:47
 *
 * LeetCode-20 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 */
public class EasyAlgorithm_5 {


    public static void main(String[] args) {
        System.out.println(validString("([{}])(){}[]"));
    }


    public static boolean validString(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.add(c);
            } else {
                char temp = stack.pop();
                String tempStr = String.valueOf(temp).concat(String.valueOf(c));
                if (tempStr.equals("()") || tempStr.equals("[]") || tempStr.equals("{}")) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.empty();
    }


}
