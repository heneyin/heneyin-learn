package com.henvealf.learn.java8.one;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 比较并行与串行的速度，大概快 10 倍
 * Created by hongliang.yin/Henvealf on 2017/11/21.
 */
public class ManyCPUCompare {


    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        for (int i = 0; i < 30000; i ++) {
            list.add(new Apple("green",i + i));
        }

        long nowTime = System.currentTimeMillis();
        List<Apple> heavyApples = list.stream().filter(x -> x.getWeight() > 4000).collect(Collectors.toList());
        long thenTime = System.currentTimeMillis();
        System.out.println("no parallel ： " + (thenTime - nowTime));


        long nowTime1 = System.currentTimeMillis();
        List<Apple> heavyApples1 = list.parallelStream().filter(x -> x.getWeight() > 4000).collect(Collectors.toList());
        long thenTime1 = System.currentTimeMillis();
        System.out.println("use parallel ： " + (thenTime1 - nowTime1));
    }

}
