package com.henvealf.learn.java.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * <br>使用“持久性”，
 * <br>有时候，我们想使用序列化来保存程序某一时刻的状态，然后我们能够很容易的将程序恢复到记录的状态。
 * <br>这样看来可行，不过如过我们的程序中，有两个对象中都有指向了第三个对象的引用，当前两个对象恢复的时候,第三个对象是出现几次那？
 * <br>一次，还是两次,我们来试验一下
 * <br>结果便是只出现一次。
 * <br>Created by Henvealf on 2016/9/6.
 */

class House implements Serializable {}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;
    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }

    public String toString() {
        return name + "[" + super.toString() + "], " + preferredHouse + "\n";
    }
}
public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house  = new House();
        List<Animal> animals = new ArrayList<>();
        //他们的马是同一匹马
        animals.add(new Animal("一只狗",house));
        animals.add(new Animal("鸭子！！鸭子",house));
        animals.add(new Animal("小猫！！小猫",house));
        System.out.println("animals : " + animals);
        System.out.println("-----------------------------------------");

        // 进行序列化，写入内存中。
        ByteArrayOutputStream buff1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buff1);
        // 两个对象
        o1.writeObject(animals);
        o1.writeObject(animals);

        //第二块缓冲内存
        ByteArrayOutputStream buff2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buff2);
        // 只一个
        o2.writeObject(animals);


        //现在反序列化
        ObjectInputStream in1 = new ObjectInputStream(
                new ByteArrayInputStream(buff1.toByteArray()));

        ObjectInputStream in2 = new ObjectInputStream(
                new ByteArrayInputStream(buff2.toByteArray()));
        List
           animals1 = (List) in1.readObject(),
           animals2 = (List) in1.readObject(),
           animals3 = (List) in2.readObject();
        System.out.println("animals1: " + animals1);
        System.out.println("animals2: " + animals2);
        System.out.println("animals3: " + animals3);
    }
}
