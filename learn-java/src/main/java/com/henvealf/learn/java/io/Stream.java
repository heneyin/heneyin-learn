package com.henvealf.learn.java.io;

import java.io.*;
import java.util.zip.ZipEntry;

/**
 * Created by Henvealf on 2016/8/31.
 */
public class Stream {
    //输入流
    InputStream inputStream;
    public void showFunction() throws IOException {
        //从流中读取相应类型的数据（至于是什么类型，就要看从其派生的输入流的类型了）。
        inputStream.read();

        byte[] b;
        int offset = 0;
        int length = 0;
        //在read()的基础上， 其中byte类型的数组是一个出参,从数据流读出的数据将会被放到b中，在后面供使用。
        //这里通过使用offset（偏移量）和length对数据的读出做了一些规定，
        //即读出的数据是从第offset个Byte开始读，一共读取length个,一般length就使用类似于b.length()这种;
        //int返回值，-1 表示读取到底了end of stream，没有再多的数据可以读，
        //一般用于在while()循环中判断是否读取完成。 正数则是读出了几个字节的数据。
        //inputStream.read(b,offset,length);

        //这个其实值调用了read(b, 0, b.length);
        //inputStream.read(b);

    }

    //允许将内存的缓冲区当做InputStream 使用,数据将按字节(byte)类型的数组被取出。
    ByteArrayInputStream byteArrayInputStream;

    //能够将String转化为数据流。看样子已经过时。
    StringBufferInputStream stringBufferInputStream;

    //将文件作为输入流，除了基本的read()等等，还有open()方法用于打开文件指针，close()关闭文件指针。
    FileInputStream fileInputStream;

    //产生用于写入到 管道输出流 PipedOutputStream 的管道数据。作为多线程的数据源
    PipedInputStream pipedInputStream;

    //用于合并输入流，将两个或多个InputStream转换成单一 InputStream.
    SequenceInputStream sequenceInputStream;

    //输入流的过滤器。作为“装饰器”的接口。
    //FilterInputStream filterInputStream;

    //---------------------------------------------

    OutputStream outputStream;


    //-----------------------
    FilterInputStream filterInputStream;

    DataInputStream dataInputStream;

    InputStreamReader inputStreamReader;

    ZipEntry zipEntry;

}
