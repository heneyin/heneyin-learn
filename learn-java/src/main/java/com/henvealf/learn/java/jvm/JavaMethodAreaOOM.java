package com.henvealf.learn.java.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * VM args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * mock JavaMethodAreaOOM
 * 1.8 之后,方法区已经被元空间替代。-XX:PermSize=10M -XX:MaxPermSize=10M 已经没有作用。
 *     需要使用 -XX:MaxMetaspaceSize 来限制元空间大小，否则元空间会自动扩展而只会限制与系统资源。
 * need CGLib
 * @author hongliang.yin/Henvealf on 2018/11/4
 */
public class JavaMethodAreaOOM {
          // MetaspaceOOM

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

                    return methodProxy.invokeSuper(o, args);
                }
            });
            enhancer.create();
            System.out.println("11");
        }
    }

    static class  OOMObject {

    }

}
