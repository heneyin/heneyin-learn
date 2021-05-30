package com.henvealf.learn.java.concurrency;

import java.io.IOException;

/**
 * 模拟图形程序，前台程序等待用户输入，同时有一个后台程序进行一些计时任务。
 * Created by Henvealf on 2016/8/16.
 */

class UnresponsiveUI {
    private volatile double d;

    public UnresponsiveUI() throws IOException {
        System.out.println("哈哈，我是对话窗口，请输入一个值：");
        System.in.read();
    }
}

public class ResponsiveUI extends Thread{
    private static volatile double d = 1;
    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
                d ++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main (String[] args) throws IOException {
        new ResponsiveUI();
        new UnresponsiveUI();
        System.out.println("啦啦啦，前台对话窗口的大约运行了有： " + d + " 秒！");
    }
}
