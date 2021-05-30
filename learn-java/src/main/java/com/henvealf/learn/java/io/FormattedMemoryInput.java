package com.henvealf.learn.java.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * 格式化内存输入，这里是Byte
 * Created by Henvealf on 2016/9/2.
 */
public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        try{
            // 当不清楚输入流的格式时，可以使用该类.
            DataInputStream in = new DataInputStream(
                    // 以字节数组作为输入流
                    new ByteArrayInputStream(
                            BufferedInputFile.read("BufferedInputTest.in").getBytes())
            );
            while(true) {
                // 读出来中文是乱码，是因为从该输入流读出的数据是以字节为单位的，而汉字占两个字节。
                // 所以汉字读出来后编码会被一分为二，然后就出现乱码。
                System.out.print((char)in.readByte());
            }
        } catch (EOFException e) {
            // 因为是按字节读出字符，所以所有字节的值都是合法的，
            // 因此返回值检测不到输入是否结束，需要使用EOF异常来检测文件的结束
            // 但是这被认为是异常的一种错误的使用的使用方式
            System.out.println("End of Stream");
        }

    }

}
