package com.henvealf.learn.java8.one;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/21.
 */
class Apple {
    private String color;
    private int weight;


    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }


    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: apples) {
            if (p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

}
