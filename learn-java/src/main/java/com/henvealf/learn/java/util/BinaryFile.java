package com.henvealf.learn.java.util;

import java.io.*;

/**
 * 读取二进制文件的工具
 * Created by Henvealf on 2016/9/2.
 */
public class BinaryFile {
    public static byte[] read(File bFile) throws IOException {
        BufferedInputStream bf = new BufferedInputStream(
                new FileInputStream(bFile)
        );
        try {
            byte[] data = new byte[bf.available()];
            bf.read(data);
            return data;
        } finally {
            bf.close();
        }
    }

    public static byte[] read(String bFile) throws IOException {
        return read(new File(bFile).getAbsoluteFile());
    }

    public static void writeToFile(byte[] bytes, File bFile) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(bFile));

        try {
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static void writeToFile(byte[] bytes, String bFile) throws IOException {
        writeToFile(bytes, new File(bFile));
    }
}
