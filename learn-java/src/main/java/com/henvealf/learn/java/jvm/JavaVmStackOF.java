package com.henvealf.learn.java.jvm;

/**
 * mock StackOverflowError
 * VM args: -Xss128k
 * @author hongliang.yin/Henvealf on 2018/11/4
 */
public class JavaVmStackOF {
    private int stackLength = 1;

    public void stackLeak() {
        long l = 1L;
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVmStackOF oom = new JavaVmStackOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
