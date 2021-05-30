package com.henvealf.learn.java.io;

import java.io.*;

/**
 * 基本的文件输出
 * Created by Henvealf on 2016/9/2.
 */
public class BasicFileOutput {
    static String file = "BasicFileOutput.out";
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(BufferedInputFile.read("BufferedInputTest.in"))
        );
        PrintWriter out = new PrintWriter(
                //增加缓冲区，提高写效率
                new BufferedWriter(new FileWriter(file))
        );
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null)             //读出来
            out.println(lineCount++ + ": " + s);        //写进去
        //刷新清空缓冲区，以防其他文件不完整。
        out.close();
        System.out.println(BufferedInputFile.read(file));

    }
}
