package com.ws.multi.thread;

/**
 * @Author: wangshuo
 * @Date: 2022/5/5 16:26
 */
public class ThreadCreateDemo1 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("主线程");

    }

    static class MyThread extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("继承Thread类实现现成");
        }
    }
}
