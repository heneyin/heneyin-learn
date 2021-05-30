package com.henvealf.learn.java.innerclass;

/**
 * Created by Henvealf on 2016/9/7.
 */
public class TestParcel {
    public static void main(String[] args) {
        Parcel4 p4 = new Parcel4();
        Contents c = p4.contents();
        Destination d = p4.destination();
        System.out.println(c.value());
        System.out.println(d.readLine());
        Parcel4.PDestination pp = p4.new PDestination();
    }
}