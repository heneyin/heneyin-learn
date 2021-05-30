package com.henvealf.learn.java.concurrencyaction;


import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <p>
 *  线程取消正常退出的示例，通常使用中断发起中断请求。
 *  Java编程实战 7-5
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-03-12
 */
public class InterruptProducter extends Thread {

    private final BlockingQueue<BigInteger> queue;

    public InterruptProducter() {
        this.queue = new ArrayBlockingQueue<>(10);
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                // 如果在这里阻塞住了，也会正常班退出。
                System.out.println("begin put data to queue...");
                this.queue.put(p.nextProbablePrime());
                System.out.println("put data to queue end...");
            }
        } catch (InterruptedException e) {
            // 线程退出。
            System.out.println("cancel because interrupt");
        }

    }

    public void cancel() {
        interrupt();
    }

    public static void main(String[] args) {
        InterruptProducter producter = new InterruptProducter();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                producter.cancel();
            }
        }.start();
        producter.start();
    }


}
