package com.ws.multi.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wangshuo
 * @Date: 2022/11/28 15:46
 *
 * 三类线程安全问题（一）运行结果 错误
 */
public class Wrong1Result {

    //volatile static int i;//非线程安全
    volatile  static AtomicInteger i = new AtomicInteger(0);// 线程安全
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100000000; j++) {
                    //i++;
                    i.getAndAdd(1);
                }
            }
        };
        Thread thread1 = new Thread(r);
        thread1.start();
        Thread thread2 = new Thread(r);
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);

    }

}
