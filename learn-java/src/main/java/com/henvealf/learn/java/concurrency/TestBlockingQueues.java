package com.henvealf.learn.java.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 使用同步队列 BlockingQueue 解决任务的协作问题。
 * 同步队列在任何时刻只允许一个任务插入或移除队列中的元素
 * 将多个LiftOff的执行进行串行化
 * Created by Henvealf on 2016/8/20.
 */
class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;
    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        this.rockets = queue;
    }

    public void add(LiftOff lo) {
        try {
            //将任务加入队列
            rockets.put(lo);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during put()");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //从队列中拿出一个任务
                LiftOff rocket = rockets.take();
                // go! go! go!
                rocket.run();
            }
        } catch (InterruptedException e) {
            System.out.println("Waking from take()");
        }
        System.out.println("Exiting LiftOffRunner");
    }

}
public class TestBlockingQueues {

    static void getKey() {
        try {
            //为了弥补Windows/Linux中Enter键所产生的额长度不同的问题
            new BufferedReader(
                    new InputStreamReader(System.in)
            ).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void getKey(String message) {
        System.out.println(message);
        getKey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue) {
        System.out.println(msg);
        //初始化运行器，传入一个同步队列。。
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t =  new Thread(runner);
        t.start();          //驱动同步队列。
        //先开始线程才能加入新任务到队列中
        for (int i = 0; i < 5; i++) {
            runner.add(new LiftOff(5));    //向同步列中增加任务
        }

        getKey("Press 'Enter' ( " + msg + " )");
        t.interrupt();

        System.out.println("Finished " + msg + " test");
    }

    public static void main(String[] args) {
        test("链接队列", new LinkedBlockingQueue<LiftOff>());
        test("固定数组队列", new ArrayBlockingQueue<LiftOff>(3));
        test("同步队列", new SynchronousQueue<LiftOff>());
    }

}
