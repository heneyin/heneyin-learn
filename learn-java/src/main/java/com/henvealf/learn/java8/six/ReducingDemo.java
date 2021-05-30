package com.henvealf.learn.java8.six;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.summarizingInt;

/**
 *
 * Created by hongliang.yin/Henvealf on 2017/11/29.
 */
public class ReducingDemo {

    public static void main(String[] args) {

        IntStream intStream = IntStream.of(1,3,4,6,2,33,12,3,5,53);

        int result = intStream.sum();
        System.out.println(result);

        List<Food> foods = new ArrayList<>();
        foods.add(new Food(13, "one"));
        foods.add(new Food(34, "two"));
        foods.add(new Food(23, "three"));
        foods.add(new Food(52, "for"));
        foods.add(new Food(36, "sd"));

        IntSummaryStatistics statistics = foods.stream().collect(summarizingInt(Food::getCalories));
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getAverage());

    }

}
