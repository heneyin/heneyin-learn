package com.henvealf.learn.java.concurrency;

/**
 * 偶数生成器
 * Created by Henvealf on 2016/8/16.
 */
public class EvenGenerator extends IntGenerator{
    private int startNum = 0;

    @Override
    public int next() { // 添加synchronized关键字就安全了
        ++ startNum;
        //这里很危险
        ++ startNum;
        return startNum;
    }

    public static void main(String[] args){
        // 因为所有的任务使用同一个生成器对象，
        // 所有所有的任务就共享了该对象的资源，并可以同时对这些资源进行修改，
        // 这里的就是指startNum；很容易就会发生资源竞争，导致任务中的生成器出错
        EventChecker.test(new EvenGenerator());
    }
}
