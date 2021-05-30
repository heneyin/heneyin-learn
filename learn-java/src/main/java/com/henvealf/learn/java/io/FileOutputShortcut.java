package com.henvealf.learn.java.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * 文件输出的简便写法
 * Created by Henvealf on 2016/9/2.
 */
public class FileOutputShortcut {

    static String file = "BasicFileOutput.out";
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(BufferedInputFile.read("BufferedInputTest.in"))
        );
        //这样就行，其中内置了缓存。
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        //读之前会先将文件中的数据清空
        while((s = in.readLine()) != null) {
            out.println(lineCount ++ + ": " + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
