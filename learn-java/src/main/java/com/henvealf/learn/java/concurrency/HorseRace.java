package com.henvealf.learn.java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 使用CyclicBarrier模拟赛马
 * 类名翻译过来就是循环栅栏，意思是设置一个类似于栅栏的点，只有当所有的任务到达栅栏后，该框架才会让任务们继续一同执行。
 * Created by Henvealf on 2016/8/21.
 */
class Horse implements Runnable {

    private static int counter = 0;
    private final int id = counter ++;
    private int strides = 0;        //奔跑的进度
    private static Random rand = new Random(47);
    //
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b) {
        this.barrier = b;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += rand.nextInt(3);
                }
                //此处就是栅栏
                barrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}
public class HorseRace {
    static final int FINISH_LINE = 30;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exec =
            Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int nHorses, final int pause) {

        // 到达栅栏后做的事情
        barrier = new CyclicBarrier(nHorses, () -> {
            //输出墙壁
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < FINISH_LINE; i++) {
                s.append("=");
            }
            System.out.println(s);

            //输出马当前跑的进度。
            for (Horse horse: horses ) {
                System.out.println(horse.tracks());
            }

            for (Horse horse : horses) {
                //有获胜的马，输出
                if (horse.getStrides() >= FINISH_LINE - 1) {
                    System.out.println(horse + "own!");
                    exec.shutdownNow();
                    return;
                }
            }
            //没有，延时一会，输出下一帧
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorse = 7;
        int pause = 200;
        new HorseRace(nHorse, pause);
    }
}
