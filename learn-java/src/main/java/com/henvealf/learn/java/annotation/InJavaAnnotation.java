package com.henvealf.learn.java.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */



public class InJavaAnnotation extends GoodBoy{

    @Override
    public void say() {
        super.say();
    }

//    public void bug() {
//        String isNull = null;
//
//        // @SuppressWarnings("all")
//        List list = new ArrayList();
//        // for ..
//    }

    public static void main(String[] args) {
        GoodBoy goodBoy = new GoodBoy();
        goodBoy.say();
        goodBoy.said();
    }
}
