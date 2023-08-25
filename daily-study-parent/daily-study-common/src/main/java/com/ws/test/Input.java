package com.ws.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: wangshuo
 * @Date: 2023/7/19 17:55
 */
public class Input {



    public static Map<String,Exam> examMap = new HashMap<>();
    public static void main(String[] args) {
        String inputString = "";
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            inputString = sc.nextLine();
            String[] inputArr = inputString.split(" ");
            if (null == inputArr || 4 != inputArr.length) {
                System.out.println("输入参数错误，请重新输入");
            }else{
                Exam exam = new Exam();
                exam.setUserName(inputArr[0]);
                exam.setBianhao(inputArr[1]);
                exam.setKemu(inputArr[2]);
                exam.setChengji(Double.valueOf(inputArr[3]));
                examMap.put(exam.getBianhao(),exam);
            }
           /* if (null == inputArr || 4 != inputArr.length) {
                System.out.println("输出：输入参数错误");
            } else{
                examMap.compute( inputArr[1], (k, v) -> {
                    Exam exam = new Exam ();
                    for (int i = 0; i < inputArr.length; i++) {
                        switch (i) {
                            case 0:
                                exam.setUserName(inputArr[i]);
                                break;
                            case 1:
                                exam.setBianhao(inputArr[i]);
                                break;
                            case 2:
                                exam.setKemu(inputArr[i]);
                                break;
                            case 3:
                                exam.setChengji(Double.valueOf(inputArr[i]));
                                break;
                            default:
                                break;
                        }
                    }
                    return exam;
                });
            }*/
            System.out.println("当前已录入的考生成绩数量为:" + examMap.size() +"条");
        }

    }
}
