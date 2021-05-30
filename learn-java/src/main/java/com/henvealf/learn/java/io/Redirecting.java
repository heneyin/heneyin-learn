package com.henvealf.learn.java.io;

import java.io.*;

/**
 * 标准IO重定向，IO重定向操纵的是字节流。
 * Created by Henvealf on 2016/9/3.
 */
public class Redirecting {
    public static void main( String[] args) throws IOException {
        PrintStream console = System.out;   //只是储存一下标准输出
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream("BufferedInputTest.in")
        );
        PrintStream out = new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream("test.out")
                )
        );

        System.setIn(in);       //设置文件BufferedInputTest.in为输入流
        System.setOut(out);     //设置文件Test.out为输出流
        System.setErr(out);

        BufferedReader br = new BufferedReader(     //字符读取流为标准输入(控制台)；
                new InputStreamReader(System.in)
        );
        String s;
        while( (s = br.readLine()) != null)
            System.out.println(s);
        out.close();
        System.setOut(console);
    }
}
