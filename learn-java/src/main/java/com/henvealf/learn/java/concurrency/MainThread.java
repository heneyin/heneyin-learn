package com.henvealf.learn.java.concurrency;

/**
 * 用main线程启动一个任务
 * Created by Henvealf on 2016/8/10.
 */
public class MainThread {
    public static void main(String args[]){
        LiftOff liftOff = new LiftOff();
        liftOff.run();
    }
}
