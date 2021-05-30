package com.henvealf.learn.java.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 从流中获取通道
 * Created by Henvealf on 2016/9/3.
 */
public class GetChannel {
    //缓冲区的size。1024个字节，也就是1MB
    public static final int BSIZE = 1024;
    public static void main(String[] args) throws IOException {
        //获取文件通道，使用FileOutputStream来获取
        FileChannel fc = new FileOutputStream("test.out").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();
        //添加到文件尾部
        fc = new RandomAccessFile("test.out","rw").getChannel();
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();
        //读取文件。
        fc = new FileInputStream("test.out").getChannel();
        //为字节缓冲区分配固定内存。
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        //将数据读取到字节缓冲区中
        fc.read(buff);
        //设置当前缓冲区的指针，将此指针设置为0
        buff.flip();
        //判断缓冲区中当前指针所在的位置到缓冲区结束之间，是否还有元素？
        while (buff.hasRemaining())
            System.out.print((char)buff.get());
    }
}
