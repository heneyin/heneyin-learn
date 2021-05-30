package com.henvealf.learn.java.concurrency;

/**
 * 理解 join() 方法
 * 线程的join()方法用于阻塞“父线程？”，在子线程结束后，父进程才会继续驱动任务。
 * 如果在线程a中加入了一个线程t（t调用join()方法），则线程a将会被挂起，直到线程t的任务结束，a才恢复继续驱动任务。
 * 也可以在join()中带上一个时间参数，可以在参加者到期还未结束时，join()就会返回，停止线程。
 * join()过程中也可以被中断，即调用 interrupter()方法。
 * Created by Henvealf on 2016/8/15.
 */
class Sleeper extends Thread{

    private int duration;   //持续的时间

    /**
     *
     * @param name
     * @param sleepTime 沉睡的时间
     */
    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        //开始沉睡
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted." + "isInterrupted():" + isInterrupted());
            return;
        }
        //醒来
        System.out.println(getName() + " has awakened");
    }
}

//被参加的线程
class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name, Sleeper sleeper) {
        super (name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            System.out.println("我在等待沉睡者醒来");
            sleeper.join();
            System.out.println("沉睡者醒了");
            sleep(10000);
            System.out.println("被参与者完成了任务");
        } catch (InterruptedException e) {
            System.out.println("Interrupter");
        }
        System.out.println(getName() + " join completed");
    }
}

public class Joining {
    public static void main (String[] args) {
        Sleeper //sleeper = new Sleeper("Sleepy", 10000),
                grumpy = new Sleeper("grumpy", 10000);
        Joiner //dopey = new Joiner("Dopey", sleeper),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}
