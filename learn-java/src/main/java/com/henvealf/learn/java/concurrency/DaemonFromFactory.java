package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用 线程工厂 Thread Factory 去创建一个后台线程
 * Created by Henvealf on 2016/8/14.
 */
public class DaemonFromFactory implements Runnable {

    @Override
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        //被重载为接受一个ThreadFactory对象。
        ExecutorService exec = Executors.newCachedThreadPool(
                new DaemonThreadFactory()
        );

        for (int i = 0; i < 10; i++) {
            //执行任务时就按照上面的开执行定制的线程
            exec.execute(new DaemonFromFactory());
        }
        System.out.println("All daemons starts");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
