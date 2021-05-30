package com.henvealf.learn.java.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 哲学家就餐问题
 * 一个哲学家
 * Created by Henvealf on 2016/8/21.
 */
public class Philosopher implements Runnable {

    private Chopstick left;         //左边的筷子
    private Chopstick right;        //右边的筷子
    private final int id;
    private final int ponderFactor; //因素
    private Random rand = new Random(47);
    private void pause() throws InterruptedException {
        if(ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    public Philosopher(Chopstick left, Chopstick right, int ident, int ponder) {
        this.left = left;
        this.right = right;
        this.id = ident;
        this.ponderFactor = ponder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + " thinking");
                pause();
                System.out.println(this + " " + " 得到右边的筷子");
                right.taken();
                System.out.println(this + " " + " 得到左边的筷子");
                left.taken();
                System.out.println(this + " " + " 开始吃");
                pause();
                System.out.println(this + " " + " 放下右边的筷子");
                right.drop();
                System.out.println(this + " " + " 放下左边的筷子");
                left.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + " exiting via interrupted");
        }
    }

    @Override
    public String toString() {
        return "哲学家 " + id;
    }
}
