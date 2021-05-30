package com.henvealf.learn.java8.six;

/**
 * Created by hongliang.yin/Henvealf on 2017/11/29.
 */
public class Food {
    private int calories;
    private String name;

    public Food(int calories, String name) {
        this.calories = calories;
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

}
