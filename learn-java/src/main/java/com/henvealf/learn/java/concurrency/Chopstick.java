package com.henvealf.learn.java.concurrency;

/**
 * 哲学家们要争抢的筷子
 * Created by Henvealf on 2016/8/21.
 */
public class Chopstick {
    private boolean taken = false;
    public synchronized void taken() throws InterruptedException {
        while(taken) {
            wait();
        }
        taken = true;
    }
    public synchronized void drop(){
        taken = false;
        notifyAll();
    }
}
