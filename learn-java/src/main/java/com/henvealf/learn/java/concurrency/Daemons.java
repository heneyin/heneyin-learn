package com.henvealf.learn.java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程大量生成其他线程
 * 生成的子线程也会默认设置为后台线程。
 * Created by Henvealf on 2016/8/14.
 */
class Daemon implements Runnable {

    private Thread[] threads = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new DaemonSpawn());
            threads[i].start();
            System.out.print("DaemonSpawn " + i + " started.");
        }

        for (int i = 0; i < threads.length; i++) {
            System.out.print("thread[" + i + "].isDeamon() = " + threads[i].isDaemon() + ", ");
        }
        while (true) {
            Thread.yield();
        }
    }

    private class DaemonSpawn implements Runnable {

        @Override
        public void run() {
            while(true) {
                Thread.yield();
            }
        }
    }
}
public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.print("d.isDaemon() = " + d.isDaemon() + ", ");
        TimeUnit.SECONDS.sleep(1);
    }

}