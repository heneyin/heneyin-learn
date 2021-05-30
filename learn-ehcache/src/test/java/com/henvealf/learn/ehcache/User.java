package com.henvealf.learn.ehcache;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-12-17
 */
public class User {
    private String name;
    private int age;
    private int grade;

    public User(String name, int age, int grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
