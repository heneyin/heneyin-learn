package com.henvealf.learn.java.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发类库中的Locks允许你去放弃去试图获得的锁。
 * Created by Henvealf on 2016/8/17.
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void untimed() {    //不定时
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if(captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            //参数的意思是，让任务消耗一段时间去获得这个锁。在这里当2秒之后仍未获得锁，将放弃就失败。
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        } finally {
            if(captured) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();

        al.untimed();   //TURE 锁有效
        al.timed();     //TURE 锁有效

        //现在创建一个单独的任务去霸占锁
        new Thread() {
            { setDaemon(true);}

            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        //给予上面线程足够的时间去霸占锁
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.yield();  //给第二个任务一个机会
        al.untimed();    //失败，锁被线程霸占了
        al.timed();      //失败，锁被线程霸占了
    }
}
