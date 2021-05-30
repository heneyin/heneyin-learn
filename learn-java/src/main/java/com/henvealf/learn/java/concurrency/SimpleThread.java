package com.henvealf.learn.java.concurrency;

/**
 * 简单用法，直接继承自Thread类
 * Created by Henvealf on 2016/8/15.
 */
public class SimpleThread extends Thread{
    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        super(Integer.toString(++threadCount));
        start();
    }

    public String toString(){
        return "#" + getName() + "(" + countDown + ")";
    }

    @Override
    public void run() {
        while(true) {
            System.out.println(this);
            if(--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
