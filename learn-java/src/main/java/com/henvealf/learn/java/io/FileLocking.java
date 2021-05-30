package com.henvealf.learn.java.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * 文件加锁。
 * 允许同步访问某个作为共享资源的文件。
 * 即多个线程访问文件时。
 * Created by Henvealf on 2016/9/5.
 */
public class FileLocking {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream fos = new FileOutputStream("test.out");
        FileLock fl = fos.getChannel().tryLock();
        if(fl != null) {
            System.out.println("Locked File");
            TimeUnit.MILLISECONDS.sleep(10000);
            fl.release();
            System.out.println("Release Lock");
        }
        fl.close();
    }

}
