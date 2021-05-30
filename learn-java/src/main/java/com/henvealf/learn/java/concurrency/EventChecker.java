package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.jar.Pack200;

/**
 * 一个偶数检查器(也是一个任务)，用于生成器生成的整数是否为偶数，如果检查出，就结束当前任务
 *
 * Created by Henvealf on 2016/8/16.
 */
public class EventChecker implements Runnable {
    private IntGenerator generator;
    private final int id;
    public EventChecker(IntGenerator g, int indent) {
        this.generator = g;
        id = indent;
    }
    @Override
    public void run() {
        while(!generator.isCanceled()) {
            int val = generator.next();
            //检查
            if(val % 2 != 0) {
                System.out.println(val + " not even");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator gp, int count){
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EventChecker(gp,count));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator gp) {
        test(gp,10);
    }
}
