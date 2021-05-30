package com.henvealf.learn.java.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 从标准输入(在控制台上的输入)中读取数据
 * Created by Henvealf on 2016/9/3.
 */
public class Echo {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String s;
        while((s = stdin.readLine()) != null && s.length() != 0){
            System.out.println(s);
        }
    }
}
