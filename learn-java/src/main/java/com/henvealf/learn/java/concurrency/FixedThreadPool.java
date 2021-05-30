package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool 可以限制线程池的大小。
 * Created by Henvealf on 2016/8/14.
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
