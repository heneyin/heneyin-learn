package com.henvealf.learn.java.concurrency;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 会发生死锁的哲学家们
 * Created by Henvealf on 2016/8/21.
 */
public class DeadLockingDiningPhilosophers {
    public static void main(String[] args) throws InterruptedException, IOException {
        int ponder = 5;
        if(args.length > 0)
            ponder = Integer.parseInt(args[0]);
        int size = 5;
        if(args.length > 1)
            size = Integer.parseInt(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            //解决死锁
            if(i < size - 1) {
                exec.execute(new Philosopher(sticks[i],sticks[(i + 1) % size], i, ponder));
            }else {
                exec.execute(new Philosopher(sticks[0],sticks[i], i, ponder));
            }
            //单独运行下面的代码，会产生死锁
            //exec.execute(new Philosopher(sticks[i],sticks[(i + 1) % size], i, ponder));
        }
        if(args.length == 3 && args[2].equals("timeout")) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}