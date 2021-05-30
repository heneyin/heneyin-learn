package com.henvealf.learn.designpattern.observer;

/**
 *
 * Created by hongliang.yin/Henvealf on 2017/11/14.
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new Subject();

        Observer addOne = new AddOneObserver();
        Observer addTwo = new AddTwoObserver();

        subject.addObserver(addOne);
        subject.addObserver(addTwo);

        subject.notfy();

        System.out.println("one: " + addOne);
        System.out.println("two: " + addTwo);

        /**--------------------------------**/
        subject.removeObserver(addOne);

        subject.notfy();

        System.out.println("one: " + addOne);
        System.out.println("two: " + addTwo);
    }
}
