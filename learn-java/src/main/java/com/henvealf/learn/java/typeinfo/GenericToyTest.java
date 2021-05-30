package com.henvealf.learn.java.typeinfo;

/**
 * 当你在Class对象上使用泛型语法时，即Class<>，你会发现newInstance()会返回一个该对象
 * 的确切类型，而不仅仅只是在ToyTest.java 中所看到的，只返回一个基本的Object。
 * Created by Henvealf on 2016/9/9.
 */
public class GenericToyTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        //使用泛型语法得到FancyToy的类型信息。
        Class<FancyToy> ftClass = FancyToy.class;
        // 初始化实例，没毛病
        FancyToy fancyToy = ftClass.newInstance();

        // 限定范围，是Fancy的超类
        Class<? super FancyToy> up = ftClass.getSuperclass();

        // 这将编译不过：
        // Class<Toy> up2 = ftClass.getSuperclass();
        //System.out.println(up2.getSimpleName());
        Object obj = up.newInstance();
    }
}
