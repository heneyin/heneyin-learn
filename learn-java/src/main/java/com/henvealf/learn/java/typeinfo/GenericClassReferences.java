package com.henvealf.learn.java.typeinfo;

/**
 * 泛化的Class引用
 * <br>能够让Class的类型信息更加具体了一些，现在允许对Class引用所指向的Class对象的类型进行限定。
 * <br>即是控制类型的范围。
 * Created by Henvealf on 2016/9/8.
 */
public class GenericClassReferences {
    public static void main(String[] args) {
        Class intClass = int.class;

        //如此来限定类型 Class<类型>
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class;

        // 发现这里可以随意赋值
        intClass = double.class;
        // 这里就不可以，编译不通过,因为上面对类型进行了限定
        //genericIntClass = double.class;

        // 可能你也想，像下面这样应该可以，但实际上是不起作用的。
        // 虽然说Integer是Number的子类。
        // 但是他无法工作的原因是：Integer Class对象并不是Number对象的子类。诡异至极
        //Class<Number> genericNumberClass = int.class;

        //使用通配符？ 他代表“任何事物”
        Class<?> inrClass = int.class;
        intClass = double.class;

        // 使用通配符 ？ 与 extends 关键字。两者结合，来创建一个范围。
        // 在编译期就能检查出你的错误。


        // 意思还是派生自extends的任何事物。
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
        //或者其他派生于Number类型的类型信息。



    }

}
