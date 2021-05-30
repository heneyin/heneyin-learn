package com.henvealf.learn.java.concurrency;

/**
 * Thread 类的最基础使用。
 * Created by Henvealf on 2016/8/14.
 */
public class BasicThreads {
    public static void main(String[] agrs) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }
}
