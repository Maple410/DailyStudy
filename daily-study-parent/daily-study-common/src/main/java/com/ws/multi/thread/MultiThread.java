package com.ws.multi.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wangshuo
 * @Date: 2022/5/12 15:59
 */
public class MultiThread {


    //volatile static int i;

   static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                for (int j = 0; j < 1000000; j++) {
                    i.getAndIncrement();
                    //i++;
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
