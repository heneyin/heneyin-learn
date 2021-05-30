package com.henvealf.learn.java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 双重同步,在此例中，想要f()与g()两个方法能够在两个线程中无排斥执行。
 * Created by Henvealf on 2016/8/18.
 */
class DualSynch {
    private Object syncObject = new Object();
    public synchronized void f(){
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }
    }

    public void g(){
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();
            }
        }
    }

}

public class SynchObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            @Override
            public void run() {
                ds.f();
            }
        }.start();

        ds.g();     //main线程中
    }
}
