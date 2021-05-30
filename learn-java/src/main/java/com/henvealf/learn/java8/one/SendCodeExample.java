package com.henvealf.learn.java8.one;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.henvealf.learn.java8.one.Apple.filterApples;

/**
 * 一个传递代码的例子
 * Predicate 使用谓词来传递代码，可以近似的把谓词当做函数的抽象。
 * Created by hongliang.yin/Henvealf on 2017/11/21.
 */
public class SendCodeExample {



    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple("green", 10));
        list.add(new Apple("yello", 1150));
        list.add(new Apple("gfdsf", 1231));
        list.add(new Apple("fdafd", 23));
        list.add(new Apple("gfdsa", 3232));
        list.add(new Apple("green", 131));
        list.add(new Apple("green", 32));

        System.out.println(filterApples(list, Apple::isGreenApple));
        System.out.println(filterApples(list, Apple::isHeavyApple));

    }
}

