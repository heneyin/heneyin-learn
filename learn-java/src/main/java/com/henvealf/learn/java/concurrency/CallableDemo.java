package com.henvealf.learn.java.concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Callable接口的使用，以他来定义的任务，在任务完成时能够返回一个值，
 * 从 call() 中返回值
 * 而且必须使用ExecutorService.submit()方法来启动任务，然后submit()会得到并返回返回值
 * Created by Henvealf on 2016/8/14.
 */

class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " +  id;
    }
}
public class CallableDemo {
    public static void main(String[] args) {
        //获取线程池
        ExecutorService exec = Executors.newCachedThreadPool();


        ArrayList<Future<String>> results =
                new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }

        for (Future<String> fs : results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }
    }
}
