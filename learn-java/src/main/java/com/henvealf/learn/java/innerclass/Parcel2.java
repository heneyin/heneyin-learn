package com.henvealf.learn.java.innerclass;

import javax.xml.bind.SchemaOutputResolver;

/**
 * 内部类并不是外部类的一部分，是名字隐藏和组织代码的模式(当然不仅仅如此)，只是可视不可视的问题。
 * 使用内部类,由于在内部类之外的任一对象中看不到内部类的存在。
 * <br>所以需要用下面的方法在外部创建内部类的对象。
 * <br>会发现下面定义的外部类里会有一个方法，
 * <br> public Destination to (String s)
 * <br> public Contents contents()
 * <br>这些方法返回指向内部类的引用
 * <br>Created by Henvealf on 2016/9/7.
 */
public class Parcel2 {
    class Contents {
        private int i = 11;
        public int value() {
            return i;
        }
    }

    class Destination  {
        private String label;
        Destination(String whereTo){
            this.label = whereTo;
        }
        String readLabel() {
            return label;
        }
    }

    //通过该方法，获取内部类的引用。
    public Destination to (String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.ship("bei jing");
        Parcel2 q = new Parcel2();
        //通过内部类中的那两个方法，来创建内部类的对象.
        Contents c = q.contents();
        Destination d = q.to("Borneo");
    }
}
