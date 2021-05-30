package com.henvealf.learn.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者观察的目标
 * Created by hongliang.yin/Henvealf on 2017/11/14.
 */
public class Subject {
    List<Observer> observerList = null;
    public Subject() {
        observerList = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    // 这里可以使用模板方法来在 nodify 之前做一些操作
    public void notfy() {
        for (Observer observer : observerList) {
            observer.go();
        }
    }
}
