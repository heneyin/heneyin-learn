package com.henvealf.learn.java.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件。
 * 当文件过于大时，比如好几个G，这样会比系统允许的一次载入的内存空间大，
 * 使用MappedByteBuffer就能只将大文件的一部分放入内存。可以很容易的修改文件。
 * Created by Henvealf on 2016/9/5.
 */
public class LargerMappedFiles {
    static int length = 0x8FFFFFF;  //128M
    public static void main(String[] args) throws IOException {
        MappedByteBuffer out = new RandomAccessFile("test.dat", "rw")
                                .getChannel()
                                .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for(int i = 0; i < length; i++)
            out.put((byte)'x');
        System.out.println("Finishded writing");
        for(int i = 6; i < 10; i ++)
            System.out.print((char)out.get(i));
    }
}
