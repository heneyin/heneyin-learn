package com.henvealf.learn.java8;

/**
 * @author hongliang.yin/Henvealf
 * @date 2018/1/25
 */
public class TimeWatch {
    private final long nowTime = System.currentTimeMillis();

    @Override
    public String toString() {
        return (System.currentTimeMillis() - nowTime) + "ms";
    }
}
