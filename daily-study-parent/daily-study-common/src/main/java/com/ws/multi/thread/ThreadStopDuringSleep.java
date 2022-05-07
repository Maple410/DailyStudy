package com.ws.multi.thread;

/**
 * @Author: wangshuo
 * @Date: 2022/5/7 15:22
 */
public class ThreadStopDuringSleep {

    public static void main(String[] args) throws InterruptedException{

        /**
         * 如果 sleep、wait 等可以让线程进入阻塞的方法使线程休眠了，而处于休眠中的线程被中断，那么线程是可以感受到中断信号的，
         * 并且会抛出一个 InterruptedException 异常，同时清除中断信号，将中断标记位设置成 false。
         * 这样一来就不用担心长时间休眠中线程感受不到中断了，因为即便线程还在休眠，仍然能够响应中断通知，并抛出异常。
         */

        Runnable runnable = () -> {

            int num = 0;
            try {

                while (!Thread.currentThread().isInterrupted() && num <= 1000) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    System.out.println(num);

                    num++;

                    Thread.sleep(1000000);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5);

        thread.interrupt();


    }
}
