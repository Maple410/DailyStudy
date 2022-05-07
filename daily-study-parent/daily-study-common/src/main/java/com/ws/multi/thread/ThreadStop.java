package com.ws.multi.thread;

/**
 * @Author: wangshuo
 * @Date: 2022/5/7 14:48
 */
public class ThreadStop implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println("count = " + count++);
        }

    }


    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new ThreadStop());

        thread.start();

        Thread.sleep(5);
        thread.interrupt();

    }
}
