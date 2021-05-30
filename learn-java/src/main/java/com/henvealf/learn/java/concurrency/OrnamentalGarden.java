package com.henvealf.learn.java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 仿真花园
 * 实现突然总结任务
 * Created by Henvealf on 2016/8/18.
 */

/**
 * 一个计数器
 */
class Count{

    private int count = 0;
    private Random random = new Random(47);

    /**
     * 计数加一
     * @return
     */
    public synchronized int increment(){
        int temp = count;
        if(random.nextBoolean())    //会有一半的概率让步
            Thread.yield();
        return (count = ++ temp);
    }

    /**
     * 查看计数值
     * @return
     */
    public synchronized int value(){
        return count;
    }
}

/**
 * 代表花园的入口
 */
class Entrance implements Runnable {
    //静态的计数器
    private static Count count =  new Count();

    //许多入口，记录入口
    private static List<Entrance> entrances =
            new ArrayList<>();

    private int number;

    //不需要对 id 进行同步
    private final int id;

    private static volatile boolean canceled = false;

    //对volatile字段进行原子性操作
    public static void cancel(){
        canceled = true;
    }

    /**
     *
     * @param id 入口的编号
     */
    public Entrance(int id){
        this.id = id;
        //将该任务放进一个List中，也能阻止死亡进程的垃圾集合。
        entrances.add(this);
    }

    @Override
    public void run() {
        while(!canceled) {
            //进入一个人
            synchronized (this) {
                ++ number;
            }
            System.out.println(this + " Total: " + count.increment());

            try{
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
            }
        }
        System.out.println("Stop: " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }


    public static int getTotalCount() {
        return count.value();
    }

    /**
     * 计算进入了花园的全部人数
     * @return
     */
    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance :
                entrances) {
            sum += entrance.getValue();
        }
        return sum;
    }
}

public class OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance(i));
        }
        //运行一段时间，然后停止后收集数据。
        TimeUnit.SECONDS.sleep(3);

        Entrance.cancel();
        exec.shutdown();

        //判断线程们是否已经停止
        if(!exec.awaitTermination(250,TimeUnit.MILLISECONDS)){
            System.out.println("Some task were not terminated");
        }

        System.out.println("Total: " + Entrance.getTotalCount());
        System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
    }
}


