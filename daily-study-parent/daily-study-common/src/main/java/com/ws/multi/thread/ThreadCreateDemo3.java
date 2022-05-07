package com.ws.multi.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: wangshuo
 * @Date: 2022/5/6 14:05
 */
public class ThreadCreateDemo3 {

    public static void main(String[] args) throws Exception {

     /*   ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> future = executorService.submit(new CallableThread());
        System.out.println(future.get());*/

        CallableThread callableThread = new CallableThread();
        FutureTask task = new FutureTask(callableThread);
        new Thread(task).start();
        System.out.println(task.get());
        System.out.println("主线程");

    }

    static class CallableThread implements Callable {
        @Override
        public Object call() throws Exception {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName());
            return 2;
        }
    }
}
