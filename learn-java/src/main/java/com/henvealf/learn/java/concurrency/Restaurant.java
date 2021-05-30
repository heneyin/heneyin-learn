package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 一个生产者消费者的演示
 * Created by Henvealf on 2016/8/19
 */

class Meal {
    private final int orderNum;

    public Meal (int orderNum) {
        this.orderNum =  orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson(Restaurant restaurant) {
        this.restaurant  = restaurant;
    }

    @Override
    public void run() {
        try {
          while(!Thread.interrupted()) {
              synchronized (this) {
                  //如果餐厅里的食物为空，则等待
                  while (restaurant.meal == null) {
                      wait();
                  }
              }
              //不为空就将食物取走
              System.out.println("侍者得到了 " + restaurant.meal);
              //视图获取厨师的的锁
              synchronized (restaurant.chef) {
                  //食物取走
                  restaurant.meal = null;
                  //给厨师一个信号
                  restaurant.chef.notifyAll();
              }
          }
        } catch (InterruptedException e) {
            System.out.println("侍者任务中断");
        }
    }
}

class Chef implements Runnable {

    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant r) {
        this.restaurant = r;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    //食物还没有被取走，就等待
                    while(restaurant.meal != null) {
                        wait();
                    }
                }

                //新增有个食物，
                if(++ count == 10) {
                    System.out.println("食物卖光，关门");
                    restaurant.exec.shutdownNow();
                 }

                System.out.print("Order up");

                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("厨师--任务中断");
        }
    }
}

public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[]  args) {
        new Restaurant();
    }

}
