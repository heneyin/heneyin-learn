package com.henvealf.learn.java.concurrency;

/**
 * 一个整型生成器
 * Created by Henvealf on 2016/8/16.
 */
public abstract class IntGenerator {

    //boolean类型是原子性的，所以像赋值和返回值这样的简单操作在发生时没有中断的可能。
    private volatile boolean canceled = false;

    public abstract int next();

    //能够被关闭
    public void cancel(){
        this.canceled = true;
    };

    public boolean isCanceled(){
        return canceled;
    }

}
