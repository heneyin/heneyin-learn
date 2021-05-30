package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 演示一下线程之间的协作。
 * wait(),notify().notifyAll()方法的使用。
 * 执行wait()方法时，会先将任务挂起，然后释放出对象锁，直到其他任务调用notify()或notifyAll()
 * Created by Henvealf on 2016/8/19.
 */

//接车类，描述了汽车打蜡与抛光两个活动，很明显这两个活动不能同时进行，只能交替进行。
class Car{

    //标志当前所做的活动，是不是在打蜡
    private boolean waxOn = false;

    //打蜡
    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    //抛光
    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    //等待去打蜡，现在正处于抛光活动，就一直挂起等待
    public synchronized void waitForWaxing() throws InterruptedException {
        while(waxOn == false) {     //等待去打蜡
            wait();
        }
    }

    //等待去抛光，如果现在正处于打蜡活动，就一直挂起等待
    public synchronized void waitForBuffing() throws InterruptedException {
        while(waxOn == true) {      //等待去抛光
            wait();
        }
    }
}

/**
 * 打蜡任务
 */
class WaxOn implements Runnable {

    public Car car;

    public WaxOn(Car car){
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                car.waitForBuffing();
                System.out.println("打蜡");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task!");
    }
}

/**
 * 抛光任务
 */
class WaxOff implements Runnable {

    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("抛光");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        //只有一辆车
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.MILLISECONDS.sleep(5000);
        exec.shutdownNow();
    }
}
