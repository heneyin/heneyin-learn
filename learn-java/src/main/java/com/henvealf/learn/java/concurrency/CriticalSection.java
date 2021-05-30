package com.henvealf.learn.java.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用同步控制块代替同步完整的方法，
 * 同时示范去保护一个非线程安全的类
 * Created by Henvealf on 2016/8/18.
 */

class Pair {    //非线程安全
    private int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Pair(){
        this(0,0);
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public void incrementX() { x ++; }
    public void incrementY() { y ++; }
    public String toString(){
        return "x: " + x + ",y: " + y;
    }

    public class PairValuesNotEqualException extends RuntimeException{
        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }
    //检查两个变量是否相同，不同就抛出异常
    public void checkState() {
        if(x != y)
            throw new PairValuesNotEqualException();
    }
}

//保护一个Pair对象是在一个线程安全的类中
abstract class PairManager {
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    //一个带有同步机制的List集合
    private List<Pair> storage =
            Collections.synchronizedList(new ArrayList<Pair>());

    //获取一份Pair的拷贝来保持原件安全
    public synchronized Pair getPair() {
        return new Pair(p.getX(), p.getY());
    }
    //假定这是一个极其消耗时间的操作
    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException ignore) {}
    }

    //自增加的方式，即同步的方式
    public abstract void increment();
}

//同步一整个方法
class PairManager1 extends PairManager {

    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(p);
    }
}

//使用同步控制块来实现同步
class PairManager2 extends PairManager {

    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            p.incrementY();
            p.incrementX();
            temp = getPair();
        }
        store(temp);
    }
}

//使用Manager操作Pair，就是进行进行自加操作
class PairManipulator implements Runnable {

    private PairManager pm;

    PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while(true) {
            pm.increment();
        }
    }

    @Override
    public String toString() {
        return "Pair: " + pm.getPair() +
                " checkCount = " + pm.checkCounter.get();
    }
}

class PairChecker implements Runnable {
    private PairManager pm;
    public PairChecker(PairManager pm) {
        this.pm = pm;
    }
    @Override
    public void run() {
        while(true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}

public class CriticalSection {
    //比较这个两个Manager不同的表现。
    static void
    testApproaches(PairManager pman1, PairManager pman2) {
        ExecutorService exec = Executors.newCachedThreadPool();

        PairManipulator
                pm1 = new PairManipulator(pman1),
                pm2 = new PairManipulator(pman2);

        PairChecker
                pchecker1 = new PairChecker(pman1),
                pchecker2 = new PairChecker(pman2);

        //在进行自加操作的时候同时检查Pair中的两个操作是否发生的错误
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pchecker1);
        exec.execute(pchecker2);

        //main主线程等待一段时间
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interruption");
        }
        //等待结束，没有出现错误，打印出最后的结果。
        System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager
                pman1 = new PairManager1(),
                pman2 = new PairManager2();

        testApproaches(pman1,pman2);
        /**
         * pm1: Pair: x: 186,y: 186 checkCount = 6382392
         * pm2: Pair: x: 190,y: 190 checkCount = 212639566
         * 表明两者都能让Pair处于线程安全。
         * 而且同步控制块比同步整个方法，能使其他线程更多的访问。
         */
    }

}
