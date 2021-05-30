package com.henvealf.learn.java.innerclass;

/**
 * 内部类的对象会与制造他的外部类对象会拥有一种联系。
 * <br>内部类能够访问外部类对象的所有成员。
 * <br>内部类还拥有其外部类的所有元素的访问权。
 * Created by Henvealf on 2016/9/7.
 */

interface Selector {
    boolean end();
    Object current();
    void next();
}

//一个队列
public class Sequence {
    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        items[next ++] = x;
    }

    //可以发现，在此内部类中，使用了外部类的成员。
    //而且还拥有所有元素的访问权。即即使是private的字段也能访问到。
    private class SequenceSelector implements Selector {
        int i = 0;
        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }
        @Override
        public void next() {
            if(i < items.length)
                i ++;
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        //先创建并初始化一个序列
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            sequence.add(Integer.toString(i));
        }
        //使用自定义的迭代器输出。
        Selector selector = sequence.selector();
        while(! selector.end()) {
            System.out.println(selector.current() + " ");
            selector.next();
        }
    }
}


