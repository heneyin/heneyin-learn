package com.henvealf.learn.java.annotation;


import com.henvealf.learn.java.annotation.an.LazyHuman;
import com.henvealf.learn.java.annotation.an.SmartRobot;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */
public class BigSystem {

    @SmartRobot()
    public void html() {
    }

    @SmartRobot()
    public void webService() {
    }

    @SmartRobot()
    public void statistical() {
    }

    @LazyHuman(name = "dt")
    public void sleep() {
    }

}
