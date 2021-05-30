package com.henvealf.learn.designpattern.observer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 没被通知，就加1
 * Created by hongliang.yin/Henvealf on 2017/11/14.
 */
public class AddOneObserver implements Observer{

    private AtomicInteger atomicInteger;

    public AddOneObserver() {
        atomicInteger = new AtomicInteger(0);
    }

    @Override
    public void go() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public String toString() {
        return atomicInteger.get() + "";
    }
}
