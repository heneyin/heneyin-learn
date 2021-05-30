package com.henvealf.learn.java.jvm;

/**
 * 测试JVM是否使用引用计数来判定对象清理。
 * @author hongliang.yin/Henvealf on 2018/11/5
 */
public class TestReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性是为了让对象占用点内存，以便能在 GC 日志中清楚的看到。
     */
    private byte[] bigSize = new byte[2* _1MB];

    public static void testGC() {
        TestReferenceCountingGC objA = new TestReferenceCountingGC();
        TestReferenceCountingGC objb = new TestReferenceCountingGC();

        // 互相引用；
        objA.instance = objb;
        objb.instance = objA;

        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }

}
