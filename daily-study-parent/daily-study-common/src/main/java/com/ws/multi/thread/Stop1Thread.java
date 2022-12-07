package com.ws.multi.thread;

/**
 * @Author: wangshuo
 * @Date: 2022/5/7 14:48
 *  使用interrupt 停止线程
 *  正确的停止线程的方式是使用 interrupt。但 interrupt 仅仅起到通知被停止线程的作用。而对于被停止的线程而言，它拥有完全的自主权，它既可以选择立即停止，
 *  也可以选择一段时间后停止，也可以选择压根不停止。
 *  贸然强制停止线程就可能会造成一些安全的问题，为了避免造成问题就需要给对方一定的时间来整理收尾工作
 *
 */
public class Stop1Thread implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println("count = " + count++);
        }

    }


    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Stop1Thread());

        thread.start();

        Thread.sleep(5);
        thread.interrupt();

    }
}
