package com.henvealf.learn.java.jmx;

import java.util.List;

/**
 * 一个将会被管理的 MBean
 * @author hongliang.yin/Henvealf on 2018/9/27
 */
public class HelloJmx implements HelloJmxMBean {

    private String name;
//    private int age;
    private List<String> hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println(name);
    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    @Override
    public void printHobby() {
        System.out.println("" +hobby);
    }
}
