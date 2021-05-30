package com.henvealf.learn.java.concurrency;

/**
 * 添加更多的线程
 * Created by Henvealf on 2016/8/14.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
