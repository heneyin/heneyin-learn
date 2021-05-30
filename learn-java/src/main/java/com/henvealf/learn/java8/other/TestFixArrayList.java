package com.henvealf.learn.java8.other;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author hongliang.yin/Henvealf
 * @date 2018/3/13
 */
public class TestFixArrayList {
    public static void main(String[] args) {
        List<Integer> fixList = new ArrayList<>(10);
        IntStream.range(0, 100).forEach(fixList::add);
        System.out.println(fixList);
    }
}
