package com.henvealf.learn.java.io;

import java.io.PrintWriter;

/**
 * Created by Henvealf on 2016/9/3.
 */
public class ChangeSystemOut {
    public static void main(String[] args) {
        //第二个参数，开启自动清空功能。
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello world");
    }
}
