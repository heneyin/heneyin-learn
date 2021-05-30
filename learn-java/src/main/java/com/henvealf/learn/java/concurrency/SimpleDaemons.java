package com.henvealf.learn.java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程(daemon):在程序运行的时候在后台提供的一种服务。
 * 并不是程序中不可或缺的部分。所以当所有非后台线程结束时，程序也就终止了，并会杀死进程中所有的后台进程。
 * Created by Henvealf on 2016/8/14.
 */
public class SimpleDaemons implements  Runnable{
    @Override
    public void run() {
        while(true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this );
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);             //将线程启动之前将线程设置为后台线程
            daemon.start();
        }
        System.out.println("All daemons srarted");
        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println("Main is end");      //当main这个线程结束后，程序也就结束了
    }
}
