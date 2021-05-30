package com.henvealf.learn.java.innerclass.controller;

/**
 * 使用内部类实现控制框架，
 * 描述了要控制的事件
 * 控制器的共同方法
 * Created by Henvealf on 2016/9/7.
 */
public abstract class Event {
    private long eventTime;         //事件开始时间
    protected final long delayTime; //延迟时间
    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    protected void start() {
        eventTime = System.nanoTime() + delayTime;
    }

    //检查是否准备成功
    public boolean ready(){
        return System.nanoTime() >= eventTime;
    }

    public abstract void action();
}
