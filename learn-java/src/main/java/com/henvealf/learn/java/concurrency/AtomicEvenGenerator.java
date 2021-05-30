package com.henvealf.learn.java.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重写{@link MutexEventGenerator}
 * 在特殊情况下才要使用它们，至于是什么特殊情况，我倒是不知道。
 * 通常依赖于锁会更安全点，synchronized 和 显式Lock
 * Created by Henvealf on 2016/8/18.
 */
public class AtomicEvenGenerator extends IntGenerator{
    private AtomicInteger currentEvenValue = new AtomicInteger();

    @Override
    public int next() {
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EventChecker.test(new AtomicEvenGenerator());
    }

}
