package com.henvealf.learn.java.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 模拟直接内存溢出
 * VM args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * @author hongliang.yin/Henvealf on 2018/11/5
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }

    }

}
