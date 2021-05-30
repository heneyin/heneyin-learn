package com.henvealf.learn.java.typeinfo;

/**
 * 一个简单的代理示例
 * Created by Henvealf on 2016/9/10.
 */

// 为了实现代理的接口
interface Interface {
    void doSomething();
    void somethingElse(String arg);
}

// 被代理的类
class RealObject implements Interface {

    @Override
    public void doSomething() {
        System.out.println("do something");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("some thing else " + arg);
    }
}

// 代理本身
class SimpleProxy implements Interface {

    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomthing");
        //在此代理了RealObject的方法的调用
        proxied.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("SimpleProxy something Else " + arg);
        proxied.somethingElse(arg);
    }
}

public class SimpleProxyDemo{

    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bilibili");
    }

    public static void main(String[] args) {
        // 自己调用
        consumer(new RealObject());
        // 使用代理调用
        consumer(new SimpleProxy(new RealObject()));
    }

}
