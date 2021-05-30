package com.henvealf.learn.java.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 2.
 * 使用 java nio 复制文件的一个示例。
 * 用到的有 channel 与 buffer
 * @author hongliang.yin/Henvealf on 2018/8/2
 */
public class CopyFileUseNio {

    public static void main(String[] args) throws IOException {
        copyFileUseNio("henvealf-learn.iml","henvealf-learn.iml.copy");
    }

    public static void copyFileUseNio(String src, String dst) throws IOException {
        FileInputStream fi = new FileInputStream(new File(src));
        FileOutputStream fo = new FileOutputStream(new File(dst));

        // get channel
        FileChannel inChannel  = fi.getChannel();
        FileChannel outChannle = fo.getChannel();

        // git buffer, 1024 byte 1kb
        ByteBuffer  bf = ByteBuffer.allocate(1024);

        while (true) {
            int eof = inChannel.read(bf);
            if (eof == -1) {
                System.out.println("Copy end.");
                break;
            }
            // 将 position 置为 0 ， limit 为 buffer 最大数据的位置，即原position的位置。等待数据从 buffer 中读出。
            bf.flip();
            outChannle.write(bf);
            // 将 position 置为 0，  limit 到 buffer 的最大容量处。等待将数据写入到 buffer 中。
            bf.clear();
        }

        inChannel.close();
        outChannle.close();
        fi.close();
        fo.close();
    }
}
