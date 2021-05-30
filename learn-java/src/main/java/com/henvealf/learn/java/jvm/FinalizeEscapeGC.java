package com.henvealf.learn.java.jvm;



/**
 * 3.2.4 使用 finalize 方法拯救即将要被回收的对象
 * 类描述信息
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("i'am still alive!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method is executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        // 调用 finalize 方法的线程优先级比较低，所以这里等待 finalize 方法被执行而拯救对象。
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("i am dead!");
        }

        // 第二次就不管用喽， finalize 只会被执行一次。
        SAVE_HOOK = null;
        System.gc();
        // 调用 finalize 方法的线程优先级比较低，所以这里等待 finalize 方法被执行而拯救对象。
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("i am dead!");
        }
    }
}
