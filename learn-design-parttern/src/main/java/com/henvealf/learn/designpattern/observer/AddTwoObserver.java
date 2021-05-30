package com.henvealf.learn.designpattern.observer;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/14.
 */
public class AddTwoObserver implements Observer {

    private int i = 0;

    public AddTwoObserver() {
    }

    @Override
    public void go() {
        i = i + 2;
    }

    @Override
    public String toString() {
        return i + "";
    }
}
