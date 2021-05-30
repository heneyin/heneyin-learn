package com.henvealf.learn.java.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 简单的动态代理，
 * 动态代理能够 动态的创建代理 并 动态的处理对所代理方法的调用，
 * Created by Henvealf on 2016/9/10.
 */

// InvocationHandler,调用处理器的实现，用来处理调用时代理所做的事
class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里做一些你想要的处理
        System.out.println("******* proxy : " + proxy.getClass() +
                ", method: " + method + ".args: " + args);
        if(args != null)
            for (Object arg : args)
                System.out.println("   " + arg);
        // 调用被代理对象中的方法。
        return method.invoke(proxied,args);
    }
}

public class SimpleDynamicProxy {

    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bilibli");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        //consumer(real);

        //在这里添加代理，
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                // 代理实现的接口的列表
                new Class[] { Interface.class},
                // InvocationHandler 接口的一个实现的实例。
                new DynamicProxyHandler(real)
        );

        //当你调用被代理类的方法的时候，将会被代理捕获到，并执行代理操作。
        consumer(proxy);
    }
}
