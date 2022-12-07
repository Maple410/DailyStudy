package com.ws.multi.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: wangshuo
 * @Date: 2022/11/29 15:43
 */
public class ForkJoinThread extends RecursiveTask<Integer> {

    int n;

    public ForkJoinThread(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        ForkJoinThread f1 = new ForkJoinThread(n - 1);
        f1.fork();
        ForkJoinThread f2 = new ForkJoinThread(n - 2);
        f2.fork();
        return f1.join() + f2.join();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask task = forkJoinPool.submit(new ForkJoinThread(i));
            System.out.println(task.get());
        }
    }
}
