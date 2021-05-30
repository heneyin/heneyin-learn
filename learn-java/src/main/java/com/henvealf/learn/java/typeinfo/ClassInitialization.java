package com.henvealf.learn.java.typeinfo;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Random;

/**
 * 类字面常量 .class
 * Created by Henvealf on 2016/9/8.
 */

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 =
            ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}


class Initable3 {
    static int staticNonFinal = 47;
    static {
        System.out.println("Initializing Initable3");
    }
}
public class ClassInitialization {
    public static Random rand = new Random(47);
    public static void main(String[] args) throws ClassNotFoundException {
        // 首先得到类型信息。在这里不会进行初始化。
        Class initable = Initable.class;

        System.out.println("After creating Initable ref");
        //访问编译期常量，发现
        //不触发初始化操作，说明常量不需要对Initable进行初始化，就可以被读取了(放在了在方法区中)
        System.out.println(Initable.staticFinal);
        System.out.println("--------------------------------------");

        // 但只是将一个字段设置为static 和 final 不能确保上面的行为。
        // 像下面的 Initable2.staticNonFinal ，由于他不是编译期常量，
        // 也就是说在编译期不能确定他的值，所以就会
        // 触发了初始化
        System.out.println(Initable.staticFinal2);
        System.out.println("------------------------------------");

        // 如果一个static字段不是final的，那么在对他访问时，会先进行链接(分配空间)和初始化(初始化该存储空间)
        // 触发了初始化
        System.out.println(Initable2.staticNonFinal);
        System.out.println("------------------------------------");

        //为了获得类型信息，forName会立刻对类进行初始化。
        Class intiable3 = Class.forName("com.henvealf.learn.typeinfo.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }

}
