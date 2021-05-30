package com.henvealf.learn.java.jvm;

/**
 *
 * 在 1.6 之后，运行时常量池已经从方法区移出.
 * @author hongliang.yin/Henvealf on 2018/11/4
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {

        // 该字符串没有在运行时常量中出现，所以该字符串引用会放入常量池中。导致true。
        String str1 = new StringBuilder("计算机软件").append("hah").toString();
        System.out.println(str1.intern() == str1);

        // java 在常量池中出现过,所以导致 str2 与 str2.intern(常量池中的不一样)
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

}
