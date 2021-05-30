package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 因为线程的本质特征，你无法捕获从线程逃逸的异常。
 * 一旦异常逃出run()方法，他就会向外传播到控制台。
 * Created by Henvealf on 2016/8/16.
 */
public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (RuntimeException e) {
            System.out.println("异常应该会捕获！"); //然而并不会
        }
    }

}
