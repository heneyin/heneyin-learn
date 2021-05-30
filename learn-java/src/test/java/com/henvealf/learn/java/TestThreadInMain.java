package com.henvealf.learn.java;


/**
 * @author hongliang.yin/Henvealf on 2018/9/19
 */
public class TestThreadInMain {


    public static void main(String[] args) {
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                    System.out.println("I am in thread");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        System.out.println("I am out of thread");
//        runnable.run();

//        System.out.println(Integer.parseInt(""));
        String s = "1231ASD";
        Object o = new Object();
        System.out.println(o);
    }

    public static void add(Integer a) {
        System.out.println(-1 % 10);
    }
}

class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
