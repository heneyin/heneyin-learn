package com.henvealf.learn.java.dyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author hongliang.yin/Heneyin
 * @date 2021/6/18
 */
public class StudentInvocationHandler<T> implements InvocationHandler {

    T target;

    public StudentInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---执行代理中的方法: " + method.getName());
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        System.out.println("---执行方法: " + method.getName() + ", 耗时: " + (System.currentTimeMillis() - startTime));
        return result;
    }

}
