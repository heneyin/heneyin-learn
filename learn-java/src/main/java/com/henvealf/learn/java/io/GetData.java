package com.henvealf.learn.java.io;

import java.nio.ByteBuffer;

/**
 * 从ByteBuffer中获取各种不同基本类型值的方法。
 * Created by Henvealf on 2016/9/4.
 */
public class GetData {
    public static final int BSIZE = 1024;
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        //这里会自动将分配后的设为0；
        int i = 0;
        while(i ++ < bb.limit())
            if(bb.get() != 0)
                System.out.println("nonozero");
        System.out.println("i = " + i);
        bb.rewind();    //会同时设置为默认字符编码。

        //存储并读取一个字符char数组。
        bb.asCharBuffer().put("Howdy");
        char c;
        while((c = bb.getChar()) != 0) {
            System.out.print(c + " ");
        }
        System.out.println();
        bb.rewind();

        //存储并读取一个short
        bb.asShortBuffer().put((short)471142);
        System.out.println("short : " + bb.getShort());
        bb.rewind();

        //存储并读取一个int
        bb.asIntBuffer().put(99471142);
        System.out.println("int : " + bb.getInt());
        bb.rewind();

        //存储并读取一个long
        bb.asLongBuffer().put(99471142);
        System.out.println("long : " + bb.getLong());
        bb.rewind();

        //储存并读取一个float
        bb.asFloatBuffer().put(99471142);
        System.out.println("float : " + bb.getFloat());
        bb.rewind();

        //储存并读取一个double
        bb.asDoubleBuffer().put(99471142);
        System.out.println("double : " + bb.getDouble());
        bb.rewind();
        System.out.println("short MAX" + Short.MAX_VALUE);
    }
}
