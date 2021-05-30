package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Created by Henvealf on 2016/8/14.
 */
public class CachedThreadPool {
    public static void main(String args[]){

        //获取开启线程池,CachedThreadPool将会给每一个任务创建一个线程。
        ExecutorService exec = Executors.newCachedThreadPool();
        //执行任务，过程中会创建线程
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        //关闭线程池,防止新任务被提交给这个Executor
        exec.shutdown();
        //shutdown之后再执行任务，将会抛出异常。
        //exec.execute(new LiftOff());
        System.out.println("我是Main王");

    }
}
