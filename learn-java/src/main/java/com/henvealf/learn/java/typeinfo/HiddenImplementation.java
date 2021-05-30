package com.henvealf.learn.java.typeinfo;

import com.henvealf.learn.java.typeinfo.packageaccess.HiddenC;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Henvealf on 2016/9/10.
 */
public class HiddenImplementation {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        A a  = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        /*if(a instanceof C) {  找不到C
            .....
        }*/

        //我的天，反射竟然可以让你继续调用个g()
        callHiddenMethod(a,"g");
        // 甚至私有方法都可以
        callHiddenMethod(a,"u");
        callHiddenMethod(a,"v");
        callHiddenMethod(a,"w");
    }

    static void callHiddenMethod(Object a, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method g = a.getClass().getDeclaredMethod(methodName);
        // 就是这里，可以允许访问私有方法。
        g.setAccessible(true);
        g.invoke(a);
    }

}
