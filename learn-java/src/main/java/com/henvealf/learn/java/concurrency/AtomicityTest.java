package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试原子性保障
 * Created by Henvealf on 2016/8/17.
 */
public class AtomicityTest implements Runnable{
    private int i = 0;
    public int getValue() { return i; }  //此处的return操作并不会完全按照原子性的保障来工作
    private synchronized void eventIncrement(){
        i++;
        i++;
    }
    @Override
    public void run() {
        while(true) {
            eventIncrement();
        }
    }
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while(true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
