package com.ws.multi.thread;

/**
 * @Author: wangshuo
 * @Date: 2022/5/5 17:10
 */
public class ThreadCreateDemo2 {

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        new Thread(myThread).start();
        System.out.println("主线程");

    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("通过Runnable接口实现");
        }
    }
}
