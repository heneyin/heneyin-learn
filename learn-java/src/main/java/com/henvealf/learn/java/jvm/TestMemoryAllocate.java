package com.henvealf.learn.java.jvm;

/**
 * <p>
 * 测试对象在内存的分配
 *
 *  -XX:+PrintGCDetails -Xms20M  -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2018/12/16
 */
public class TestMemoryAllocate {

    private static final int _1MB = 1024 * 1024;

    /**
     * -XX:+PrintGCDetails -Xms20M  -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * -XX:+PrintGCDetails -Xms20M  -Xmx20M -Xmn10M -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
     *
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation4;
        allocation4 = new byte[5 * _1MB];
    }

    /**
     * -verbose:gc -XX:+PrintGCDetails -Xms20M  -Xmx20M -Xmn10M -XX:+PrintCommandLineFlags -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     */
    public static void testTeunringThreshold() {
        byte[] allocation1,allocation2, allocation3, allocation4, allocation5;

        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB * 4];
        allocation3 = new byte[_1MB * 4];
        allocation3 = null;
        allocation4 = new byte[_1MB * 4];

    }

    public static void main(String[] args) {
//        testAllocation();
//        testPretenureSizeThreshold();
        testTeunringThreshold();
    }
}
