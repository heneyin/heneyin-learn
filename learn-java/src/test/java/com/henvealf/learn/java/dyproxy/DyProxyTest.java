package com.henvealf.learn.java.dyproxy;

import com.henvealf.learn.java.entity.Person;
import com.henvealf.learn.java.entity.Student;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author hongliang.yin/Heneyin
 * @date 2021/6/18
 */
public class DyProxyTest {

    @Test
    public void normal() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        //创建一个实例对象，这个对象是被代理的对象
        Person zhangsan = new Student("张三");

        //创建一个与代理对象相关联的InvocationHandler
        InvocationHandler stuHandler = new StudentInvocationHandler<>(zhangsan);

        //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);

        //代理执行上交班费的方法
        stuProxy.giveMoney();

    }

}
