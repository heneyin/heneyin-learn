package com.henvealf.learn.java.concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * 使用ThreadFactory来定制由Executor创建的线程的属性（后台，优先级，名称）。
 * 这里是将线程定制成了后台线程。
 * Created by Henvealf on 2016/8/14.
 */
public class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);  //定制成了后台线程。
        return t;
    }
}
