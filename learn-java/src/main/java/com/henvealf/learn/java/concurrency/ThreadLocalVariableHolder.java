package com.henvealf.learn.java.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 自动给予每个线程自己的存储,
 * 通常将ThreadLocal对象当做静态存储区。
 * 每个线程都有自己独立存储块。
 * get()方法返回的是存储块中对象的拷贝。
 * set()会将其插入到线程自己的存储块中。并返回存储中原有的对象。
 * 每个线程都会被分配到了自己的存储，即便只有一个ThreadLocalVariableHolde对象。
 * Created by Henvealf on 2016/8/18.
 */

class Accessor implements Runnable {

    private final int id;
    public Accessor(int idn) {
        id = idn;
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + ":" + ThreadLocalVariableHolder.get();
    }
}
public class ThreadLocalVariableHolder {


    private static ThreadLocal<Integer> value =
            new ThreadLocal<Integer>(){
                private Random r = new Random(47);
                protected synchronized Integer initialValue() {
                    return r.nextInt(1000);
                }
            };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}
