package com.henvealf.learn.java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程不会运行finally子句
 * Created by Henvealf on 2016/8/15.
 */
class ADaemon implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.print("Exting via InterruptedException");
        }finally {
            System.out.println("This should always run?");
        }
    }
}

public class DaemonsDontRunFinally {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
        TimeUnit.MILLISECONDS.sleep(900);
    }
}
