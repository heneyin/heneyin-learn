package com.henvealf.learn.java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 使用内部类来创建线程
 * Created by Henvealf on 2016/8/15.
 */

//使用非匿名内部类
class InnerThread1 {
    private int countDown = 5;
    private Inner inner;

    private class Inner extends Thread {

        //创建线程对象时就会自启动
        Inner(String name) {
            super(name);
            start();
        }

        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (-- countDown == 0) {
                        return;
                    }
                    sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("interruption");
            }
        }

        @Override
        public String toString() {
            return getName() + ": " + countDown;
        }
    }

    public InnerThread1(String name) {
        inner = new Inner(name);
    }

}

//使用匿名内部类定义并启动一个线程
class InnerThread2 {
    private int countDown = 5;
    private Thread t;
    public InnerThread2 (String name) {
        t = new Thread(name){

            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (-- countDown == 0) {
                            return;
                        }
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("interruption");
                }
            }

            @Override
            public String toString() {
                return getName() + ": " + countDown;
            }
        };
        t.start();
    }
}

//使用命名式的Runnable实例
class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;
    private class Inner implements Runnable {

        Thread t;
        Inner (String name){
            t = new Thread(this,name);
            t.start();
        }
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (-- countDown == 0) {
                        return;
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("interruption");
            }
        }

        @Override
        public String toString() {
            return t.getName() + ": " + countDown;
        }
    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
}
//使用匿名类的方式实现Runnable
class InnerRunnable2 {
    private int countDown = 5;
    private Thread t;
    public InnerRunnable2 (String name) {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (-- countDown == 0) {
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                } catch (InterruptedException e) {
                    System.out.println("interruption");
                }
            }
            public String toString() {
                return Thread.currentThread().getName() + ": " + countDown;
            }
        },name);
        t.start();
    }
}

//使用一个分离出来的方法来运行一个任务。
class ThreadMethod {
    private int countDown = 5;
    private Thread t;
    private String name;
    public ThreadMethod (String name) {
        this.name = name;
    }

    public void runTask() {
        if (t == null) {
            t = new Thread(name) {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(this);
                            if (-- countDown == 0) {
                                return;
                            }
                            TimeUnit.MILLISECONDS.sleep(100);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("interruption");
                    }
                }

                @Override
                public String toString() {
                    return getName() + ": " + countDown;
                }
            };
            t.start();
        }

    }
}
//
public class ThreadVariations {
    public static void main(String[] args){
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable2("InnerRunnable2");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}
