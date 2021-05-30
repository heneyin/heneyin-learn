package com.henvealf.learn.java.innerclass;

/**
 * 在方法的作用域内使用内部类
 * Created by Henvealf on 2016/9/7.
 */
public class Parcel5 {
    public Destination destination(){
        class PDestination implements Destination {

            @Override
            public String readLine() {
                return "in method";
            }
        }

        return new PDestination();
    }

    public static void main(String[] args) {
        String  s =new Parcel5().destination().readLine();
        System.out.println(s);
    }
}
