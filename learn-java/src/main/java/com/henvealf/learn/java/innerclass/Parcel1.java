package com.henvealf.learn.java.innerclass;

/**
 * 创建内部类,
 * 就是嵌套在其他类中的类。
 * Created by Henvealf on 2016/9/7.
 */
public class Parcel1 {
    class Contents {
        private int i;
        public int value() {
            return i;
        }
    }

    //目的地
    class Destination {
        private String label;
        Destination(String whereTo) {
            this.label = whereTo;
        }
        String readLabel(){
            return label;
        }
    }

    // 我们在这里使用了内部类，和使用普通的类没有什么区别。
    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");
    }
}
