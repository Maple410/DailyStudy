package com.ws.leetcode.submitted;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: wangshuo
 * @Date: 2022/3/26 16:43
 * LeetCode - 12 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 3
 * 输出: "III"
 * 示例 2:
 * <p>
 * 输入: num = 4
 * 输出: "IV"
 * 示例 3:
 * <p>
 * 输入: num = 9
 * 输出: "IX"
 * 示例 4:
 * <p>
 * 输入: num = 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: num = 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class MiddleAlgorithm_12 {


    public static void main(String[] args) {


        System.out.println(integerToRom(3));
    }

    public static String integerToRom(int num) {

        StringBuilder sb = new StringBuilder("");

        Map<Integer, String> numMap = new LinkedHashMap<>();
        numMap.put(1000, "M");
        numMap.put(900, "CM");
        numMap.put(500, "D");
        numMap.put(400, "CD");
        numMap.put(100, "C");
        numMap.put(90, "XC");
        numMap.put(50, "L");
        numMap.put(40, "XL");
        numMap.put(10, "X");
        numMap.put(9, "IX");
        numMap.put(5, "V");
        numMap.put(4, "IV");
        numMap.put(1, "I");


        for (Map.Entry<Integer, String> entry : numMap.entrySet()) {
            int k = entry.getKey();
            String v = entry.getValue();
            int remind = num % k;
            int index = num / k;
            if (index > 0) {
                for (int i = 0; i < index; i++) {
                    sb.append(v);
                }
            }
            if (remind == 0) {
                break;
            }
            num = remind;
        }
        return sb.toString();
    }
}
