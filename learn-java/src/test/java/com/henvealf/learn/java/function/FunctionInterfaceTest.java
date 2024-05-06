package com.henvealf.learn.java.function;

import org.junit.Test;

/**
 * 测试 @FunctionalInterface 注解是否可以忽略继承关系
 */
public class FunctionInterfaceTest {

    @FunctionalInterface
    public interface Creator {
        String create(String name, String value);
    }

    public static String create(String name, String value) {
        return name + value;
    }

    public String useCreatorFunction(Creator creator) {
        return creator.create("1", "2");
    }

    @Test
    public void test() {
        String s = useCreatorFunction(FunctionInterfaceTest::create);
        System.out.println(s);
    }

}
