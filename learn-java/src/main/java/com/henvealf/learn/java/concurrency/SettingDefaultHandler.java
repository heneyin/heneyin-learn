package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 为Thread类设置默认的异常处理器。即每个异常都会被该处理器处理,
 * 默认异常处理器只有在专有异常处理器捕获不到某些异常时，才会被调用，即是保全方式。
 *
 * Created by Henvealf on 2016/8/16.
 */
public class SettingDefaultHandler {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(
                new MyUncaughtExceptionHandler()
        );

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
        exec.shutdown();
    }
}
