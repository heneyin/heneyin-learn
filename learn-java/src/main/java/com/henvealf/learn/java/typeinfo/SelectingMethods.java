package com.henvealf.learn.java.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 在SimpleDynamicProxy中，我们为被代理类提供了通用的代理操作，
 * 即对不同的方法的调用插入一样的操作，这样一定会受限。
 * 我们可以使用method.getName()方法来定制在不同的方法上基于不同的代理行为。
 * Created by Henvealf on 2016/9/10.
 */

class MethodSelector implements InvocationHandler {

    private Object proxied;

    public MethodSelector(Object proxied) {
        this.proxied = proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("interesting"))
            System.out.println("proxy detected the interesting method");
        return method.invoke(proxied,args);
    }
}

interface SomeMethods {
    void boring1();
    void boring2();
    void interesting(String arg);
    void boring3();
}

class Implementation implements SomeMethods {
    public void boring1() {
        System.out.println("Boring1");
    }

    @Override
    public void boring2() {
        System.out.println("Boring2");
    }

    @Override
    public void interesting(String arg) {
        System.out.println("interesting : " + arg);
    }

    @Override
    public void boring3() {
        System.out.println("Boring3");
    }
}

public class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(
                SomeMethods.class.getClassLoader(),
                new Class[] {SomeMethods.class},
                new MethodSelector(new Implementation())
        );
        proxy.boring1();
        proxy.boring2();
        proxy.boring3();
        proxy.interesting("12312312321");
    }
}
