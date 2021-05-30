package com.henvealf.learn.java.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用 CountDownLatch 类
 * 他用来同步一个或多个任务，让他们等待由其他任务执行的一组操作的完成
 * Created by Henvealf on 2016/8/21.
 */

class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter ++;
    private static Random rand =  new Random(47);
    private CountDownLatch latch;
    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {

        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this + "completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d " ,id);
    }
}

class WaitingTask implements Runnable {

    private static int counter = 0;
    private final int id = counter ++;
    private final CountDownLatch latch;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }
    public String toString() {
        return String.format("%1$-3d " ,id);
    }
}

public class CountDownLatchDemo {
    public static final int SIZE = 100;
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        // 10个等待的任务
        for (int i = 0; i < 10; i++) {
            exec.execute(new WaitingTask(latch));
        }
        // 前提任务。
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new TaskPortion(latch));
        }
        System.out.println("Launched all tasks");
        exec.shutdown();
    }
}
