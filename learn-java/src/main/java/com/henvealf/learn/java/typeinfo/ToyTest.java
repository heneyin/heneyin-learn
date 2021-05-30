package com.henvealf.learn.java.typeinfo;

/**
 * 当你拥有了一个感兴趣类型的对象的时候，你可以通过调用getClass方法来获取类型的Class引用。<br>
 * 这个方法属于根类Object的一部分。
 * Created by Henvealf on 2016/9/8.
 */

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}

class Toy {
    Toy() {}
    Toy (int i) {}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }
}

public class ToyTest {

    static void printInfo(Class cc) {
        System.out.println("Class name: " + cc.getName() +
                        " is interface? [" + cc.isInterface() + "]");
        System.out.println("Simple name: " + cc.getSimpleName());
        System.out.println("Canonical name: " + cc.getCanonicalName());
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            //使用forName()得到FancyToy的类型信息。
            c = Class.forName("com.henvealf.learn.typeinfo.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Cant's find FancyToy");
            System.exit(1);
        }
        printInfo(c);

        //获得FancyToy所有继承的接口，并输出。
        for (Class face : c.getInterfaces())
            printInfo(face);
        System.out.println("++++++++++++++++++++++++++++++++++++++");

        // 获取FancyToy的直接父类，即Toy
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            // 根据获得的类型信息--实例化Toy，
            // 实现虚拟构造器的功能，意思是，我不知道你的类型，但是我希望你能够正确的创建自己的对象。
            // up类型必须带有默认的构造器。
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("cannot instance");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
}
