package com.bread.javaplayground.threadpool;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {

    private Thread thread = null;
    private BlockingQueue taskQueue = null;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped) {
            System.out.println("스레드 실행중");
            try {
                System.out.println("task 들어올 때까지 대기 " + thread.getName());
                Runnable runnable = (Runnable) taskQueue.take();
                System.out.println("task 처리");
                runnable.run();
            } catch (Exception e) {
                System.out.println("가져올 task가 없습니다");
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        this.thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
