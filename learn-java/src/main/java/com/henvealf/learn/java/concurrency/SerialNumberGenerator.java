package com.henvealf.learn.java.concurrency;

/**
 * 产生序列数字的类
 * Created by Henvealf on 2016/8/18.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static int nextSerialNumber() {
        return serialNumber ++;             //不是线程安全的
    }
}
