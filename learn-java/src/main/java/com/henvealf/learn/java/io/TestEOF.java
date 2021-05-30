package com.henvealf.learn.java.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * 当逐个字节读取文件时，检测文件啥时候结束
 * Created by Henvealf on 2016/9/2.
 */
public class TestEOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(
                new ByteArrayInputStream(
                        BufferedInputFile.read("BufferedInputTest.in").getBytes())
        );
        //在没有阻塞的情况下可读的字节数不为0，对于文件来说就是整个文件，而其他类型的流可能就不是了。
        while(in.available() != 0) {
            System.out.print((char)in.readByte());
        }
    }
}
