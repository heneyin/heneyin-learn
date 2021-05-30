package com.henvealf.learn.java.typeinfo;

/**
 * 通常一个实现了某个接口的类中拥有自己的非来自于接口的方法，
 * 向上转型为接口的时候，就无法通过转型后的接口对象来调用子类自己另添加的方法，
 * 这个是完全正常的，但是可以使用类型信息绕过这种限制，可以对实现类中的方法进行调用
 * Created by Henvealf on 2016/9/10.
 */

class B implements A {

    @Override
    public void f() {

    }

    public void g(){

    }
}
public class InterfaceViolation {
    public static void main(String[] args){
        A a = new B();
        a.f();
        // 这样编译不会通过
        //a.g();

        System.out.println(a.getClass().getName());
        // 看见没！先检查一下类型，然后转型调用。。。。滋滋滋，真不要脸。
        if(a instanceof B) {
            B b = (B)a;
            b.g();
        }
    }

}
