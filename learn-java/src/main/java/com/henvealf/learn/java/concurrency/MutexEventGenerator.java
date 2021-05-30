package com.henvealf.learn.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock类显示的解决资源竞争。
 * lock() 让访问该方法的任务获得锁。
 * unlock() 任务访问结束后完成后释放锁。
 * Created by Henvealf on 2016/8/17.
 */
public class MutexEventGenerator extends IntGenerator {
    private int currentEventValue = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try {
            ++ currentEventValue;
            Thread.yield();           //交换处理器，增大错误产生的可能性。
            ++ currentEventValue;
            return currentEventValue;   //return 语句必须在try子句中出现。确保数据交付完成后，再释放锁。
        } finally {
            System.out.println("哈哈哈，难道我还会再执行？？？");//对，你会被执行。
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EventChecker.test(new MutexEventGenerator());
    }
}
