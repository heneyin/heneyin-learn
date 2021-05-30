package com.henvealf.learn.java.concurrency;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by Henvealf on 2016/8/18.
 */
//这是一个需要被清理的类
class NeedsCleanup {
    private final int id;
    public NeedsCleanup(int ident) {
        this.id = ident;
        System.out.println("NeedsCleanup: " + id);
    }

    //模拟清理过程
    public void cleanup(){
        System.out.println("Clearn up " + id);
    }

}

class Bolcked3 implements Runnable {

    private volatile double d = 0.0;

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()) {
                System.out.println("-----------------------");
                NeedsCleanup n1 = new NeedsCleanup(1);
                try {

                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);

                    try {
                        System.out.println("Calculating");
                        for (int i = 0; i < 2500000; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("Finished time-consuming operations");
                    } finally {
                        //不管发生什么，finally子句里的代码都会被执行
                        n2.cleanup();
                    }

                } finally {
                    //n1运行完成后就会被清理
                    n1.cleanup();
                }
            }
            System.out.println("Exiting via while() test");
        } catch(InterruptedException e) {
            System.out.println("Exiting via InterruptedException");
        }
    }
}
public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Bolcked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(3500);
        t.interrupt();
    }
}
