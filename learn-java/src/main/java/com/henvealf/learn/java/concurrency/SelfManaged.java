package com.henvealf.learn.java.concurrency;

/**
 * 一个包含了自己的线程的任务。也就是自管理。
 * 在构造器中启动线程，很有可能会让刚启动的线程访问到正处于不稳定状态的对象，或者说是未完整创建结束的对象
 * Created by Henvealf on 2016/8/15.
 */
public class SelfManaged implements Runnable{

    private int countDown = 5;
    private Thread t = new Thread(this);
    public SelfManaged() {t.start();}

    public String toString() {
        return Thread.currentThread().getName() + "(" + countDown + "), ";
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(this);
            if(-- countDown == 0){
                return;
            }
        }
    }

    public static void main(String[] args){
        for (int i = 0; i < 5; i++) {
            new SelfManaged();
        }
    }
}
