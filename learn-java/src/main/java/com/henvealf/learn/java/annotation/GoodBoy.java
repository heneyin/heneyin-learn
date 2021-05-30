package com.henvealf.learn.java.annotation;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */

public class GoodBoy {

    public void say() {
        System.out.println("I am a good boy");
    }

    @Deprecated
    public void said() {
        System.out.println("I will destroy the whole world");
    }

}
