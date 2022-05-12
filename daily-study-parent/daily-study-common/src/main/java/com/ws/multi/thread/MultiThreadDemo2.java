package com.ws.multi.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangshuo
 * @Date: 2022/5/12 16:39
 */
public class MultiThreadDemo2 {

    private Map<Integer, String> students;

    public MultiThreadDemo2() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                students = new HashMap<>();
                students.put(1, "王小美");
                students.put(2, "钱二宝");
                students.put(3, "周三");
                students.put(4, "赵四");
            }
        }).start();
    }

    public Map<Integer, String> getStudents() {
        return students;
    }


    public static void main(String[] args) throws InterruptedException {
        MultiThreadDemo2 demo2 = new MultiThreadDemo2();
        Thread.sleep(1000);
        System.out.println(demo2.students.get(1));
    }
}
