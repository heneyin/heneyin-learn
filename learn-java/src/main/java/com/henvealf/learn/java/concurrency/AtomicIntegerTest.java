package com.henvealf.learn.java.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重写 {@link AtomicityTest}类
 * 对原子类的变量来进行的更新操作,
 * 能后保证对其的读取和复制操作是安全的原子性操作，
 * 所以就能够不使用synchronized,
 * 有:
 * AtomicInteger,
 * AtomicLong,
 * AtomicReference,
 * 等等.
 * Created by Henvealf on 2016/8/18.
 *
 */
public class AtomicIntegerTest implements Runnable{

    //原子类整型，
    private AtomicInteger i = new AtomicInteger();

    public int getValue(){
        return i.get();
    }
    private void evenIncrement(){
        //进行加操作并返回加之后的值
        i.addAndGet(2);
    }

    @Override
    public void run() {
        while(true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {

        //一个计时任务，指定时间后执行某项任务，该任务不是一个daemon任务。
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        },5000);    //5秒后终止

        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest ait = new AtomicIntegerTest();
        exec.execute(ait);
        while(true) {
            int val = ait.getValue();
            if(val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }



}
