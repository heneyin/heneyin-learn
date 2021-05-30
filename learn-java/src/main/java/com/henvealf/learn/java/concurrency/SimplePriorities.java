package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 展示线程优先级的使用
 * Created by Henvealf on 2016/8/14.
 */
public class SimplePriorities implements Runnable{
    private int countDown = 5;
    private volatile double d;
    private int priority;

    public SimplePriorities(int priority){
        this.priority = priority;
    }

    public String toString() {
        return  Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        //获得驱动当前任务的线程池的引用。并设置线程的优先级，
        //优先级最好在run（）方法开头设置
        Thread.currentThread().setPriority(priority);
        while(true) {
            //
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double)i;
                if(i % 1000 == 0) {
                   /* *
                     * 示意调度程序，当前的线程将会放弃当前使用的处理器（CPU）。调度程序可能会自主忽略这个示意。
                     * yield 是一个探索式的努力，好来提高线程之间的协调关系，来避免过度的使用一个CPU。
                     *
                     * */
                    Thread.yield();
                }
            }

            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i ++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
