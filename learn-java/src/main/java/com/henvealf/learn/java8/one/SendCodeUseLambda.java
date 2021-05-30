package com.henvealf.learn.java8.one;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 lambda 传递代码
 * Created by hongliang.yin/Henvealf on 2017/11/21.
 */
public class SendCodeUseLambda {

    public static void main(String[] args) {

        List<Apple> list = new ArrayList<>();
        list.add(new Apple("green", 10));
        list.add(new Apple("yello", 1150));
        list.add(new Apple("gfdsf", 1231));
        list.add(new Apple("fdafd", 23));
        list.add(new Apple("gfdsa", 3232));
        list.add(new Apple("green", 131));
        list.add(new Apple("green", 32));

        System.out.println(Apple.filterApples(list, (Apple a) -> a.getWeight() > 1000));;
        System.out.println(Apple.filterApples(list, (Apple a) -> "green".equals(a.getColor())));;

    }

}
