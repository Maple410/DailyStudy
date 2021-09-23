package com.ws.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: wangshuo
 * @Date: 2021/9/23 14:15
 */
public class JstackCase {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {

        Task task1 = new Task();
        Task task2 = new Task();
        executorService.execute(task1);
        executorService.execute(task2);
    }

    public static Object lock = new Object();

    static class Task implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                long sum = 0L;
                while (true) {
                    sum += 1;
                }
            }
        }
    }
}
