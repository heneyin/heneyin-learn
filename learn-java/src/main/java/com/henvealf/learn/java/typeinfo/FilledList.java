package com.henvealf.learn.java.typeinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例使用泛型类语法
 * <br>Created by Henvealf on 2016/9/9.
 */

class CountedInteger {
    private static long counter;
    private final long id = counter ++;

    @Override
    public String toString() {
        return Long.toString(id);
    }
}

public class FilledList<T> {

    private Class<T> type;

    // 你想要在List中填充的类型
    public FilledList(Class<T> type) {
        this.type = type;
    }

    //返回一个含有你在构造器中使用的类型的对象的集合
    public List<T> create(int nElement) {

        List<T> result = new ArrayList<>();
        try {
            for (int i = 0; i < nElement; i++) {
                //实例化的时候final字段会自动递增
                result.add(type.newInstance());
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        FilledList<CountedInteger> fl = new FilledList<>(CountedInteger.class);
        System.out.println(fl.create(15));
    }

}
