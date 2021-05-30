package com.henvealf.learn.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 转换数据，这里试图使用asCharBuffer()方法一次性读取处通道缓冲区ByteBuffer中的内容。
 * 然而并不可以
 * Created by Henvealf on 2016/9/3.
 */
public class BufferToText {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some test ".getBytes()));
        fc.close();
        fc = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        //哎呀，这样不好使
        System.out.println(buff.asCharBuffer());
        //倒回，将指针移动到0
        buff.rewind();

        String encoding = System.getProperty("file.encoding");
        //通过获取到的文件的编码格式来转码得到到数据。
        System.out.println("Decoding using " + encoding +
                        ":" + Charset.forName(encoding).decode(buff));
        //
        fc = new FileOutputStream("data2.txt").getChannel();
        //写数据的时候指定编码格式。
        fc.write(ByteBuffer.wrap(" Other text".getBytes("UTF-16BE")));
        fc.close();
        //现在再次读取试试
        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());

        //通过CharBuffer来写文件
        fc = new FileOutputStream("test.out").getChannel();
        //为ByteBuffer分配24空间，而空间大于锁需要的空间。
        buff = ByteBuffer.allocate(24);
        //先将ByteBuffer转为CharBuffer，然后往里面放一些字符
        buff.asCharBuffer().put("Some Text");
        //使用通道写
        fc.write(buff);
        fc.close();

        //啦啦啦，读出来看看吧。
        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        System.out.println("转化为CharBuffer后读出结果(不flip)" + buff.asCharBuffer());
        buff.flip();
        System.out.println("转化为CharBuffer后读出结果" + buff.asCharBuffer());
    }
}
