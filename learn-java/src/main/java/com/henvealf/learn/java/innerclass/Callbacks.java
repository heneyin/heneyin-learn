package com.henvealf.learn.java.innerclass;

/**
 * 回调的应用,通过回调，对象就能够携带一些信息，这些信息允许他在稍后的某个时刻调用初始的对象。
 *
 * <br>回调的意思是希写了一段程序a,你的同事能够使用这段程序调用自己的程序。
 *
 * <br>需要使用一个接口，传递实现接口的对象的引用来实现功能。
 * Created by Henvealf on 2016/9/7.
 */

//这就是那个对象
interface Incrementable {
    void increment();
}

class Callee1 implements Incrementable {

    private int i = 0;
    @Override
    public void increment() {
        i++;
        System.out.println(i);
    }
}

class MyIncrement {
    public void increment() {
        System.out.println("Other operator");
    }
    static void f(MyIncrement mi){
        mi.increment();
    }
}

class Callee2 extends MyIncrement {
    private int i = 0;

    public void increment(){
        //这个方法只是输出了一段文字。
        super.increment();

        i++;
        System.out.println(i);
    }

    //一个闭包类，这个类能够访问到外部类中的所有元素。
    private class Closure implements Incrementable {
        @Override
        public void increment() {
            Callee2.this.increment();
        }
    }

    //返回闭包
    Incrementable getCallbackPeference() {
        return new Closure();
    }
}

//
class Caller {
    private Incrementable callbackReference;
    Caller(Incrementable cbh) {
        callbackReference = cbh;
    }
    void go() {
        callbackReference.increment();
    }
}

public class Callbacks {
    public static void main(String[] args) {
        //假如此处为A程序员编写的main()方法。
        //这是普通方式完成的回调。
        //这个是程序员A自己的类实例化的对象，里面有一个increment()方法来完成自加操作。
        Callee1 c1 = new Callee1();
        //Caller类中的go()方法就实现了回调功能。他调用了A程序员的方法
        Caller caller1 = new Caller(c1);
        caller1.go();
        caller1.go();


        //下面使用了内部类
        Callee2 c2 = new Callee2();
        //MyIncrement.f(c2);
        Caller caller2 = new Caller(c2.getCallbackPeference());
        System.out.println("---------------------------------------");
        caller2.go();
        caller2.go();
    }
}
