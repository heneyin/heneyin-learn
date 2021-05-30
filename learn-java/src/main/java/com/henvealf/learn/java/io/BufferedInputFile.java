package com.henvealf.learn.java.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * 缓冲输入文件
 * Created by Henvealf on 2016/9/2.
 */
public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        //一个BufferedReader对象相当于一个输入缓冲区，用于提高输入流的读取速度，
        //所以创建对象时,所以需要传递一个输入流对象给他的构造器，比如FileReader和InputStreamReader
        //InputStreamReader又是什么那？她是桥接字节流到字符流的类。
        //按行输入。
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s = in.readLine()) != null)
            sb.append(s + "\n");
        in.close();
        return sb.toString();
    }

    public static void main(String[] args ) throws IOException {
        System.out.println(read("BufferedInputTest.in"));
    }
}
