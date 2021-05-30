package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 调用sleep()方法去暂停一会
 * Created by Henvealf on 2016/8/14.
 */
public class SleepTask extends LiftOff {

    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.print(status());
                //旧方式
                //Thread.sleep(1000);
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (InterruptedException e){
            System.out.println("Interrupted");
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SleepTask());
        }
        exec.shutdown();
    }
}