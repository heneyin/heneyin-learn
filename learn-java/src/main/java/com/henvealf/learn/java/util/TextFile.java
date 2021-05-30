package com.henvealf.learn.java.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>一个文件读写的使用工具，该类就代表一个文本文件.
 * <p>......................................   .from《Java 编程思想》
 *
 * <p>Created by Henvealf on 2016/9/2.
 */
public class TextFile extends ArrayList<String>{

    /**
     * 读取文件内容为一个String。
     * @param fileName 文件名
     * @return String 文件中的内容
     */
    public static String read(String fileName) {
        StringBuilder sb =  new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(new File(fileName).getAbsoluteFile())
            );
            try {
                String s;
                while((s = in.readLine()) != null){
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * 像文件中写文本
     * @param fileName 要写入的文件的文件名
     * @param text 要写入的文本
     */
    public static void write(String fileName, String text) {
        try {
            PrintWriter out =  new PrintWriter(
                    new File(fileName).getAbsoluteFile()
            );
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取一个文件，并用任意一个正则表达式来分割文本。
     * @param fileName
     * @param splitter
     */
    public TextFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
        //如果读取的为空，就抛弃
        if(get(0).equals("")) remove(0);
    }

    /**
     * 读取一个文件中的内容，并使用换行符分割字符串,即按行读出。
     * @param fileName
     */
    public TextFile(String fileName) {
        this(fileName, "\n");
    }

    public void write(String fileName) {
        try {
            PrintWriter out = new PrintWriter(
                    new File(fileName).getAbsoluteFile()
            );
            try {
                for (String item : this)
                    out.println(item);
            } finally{
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //简单测试
    public static void main(String[] args) {
        String fileContent = read("BufferedInputTest.in");
        write("BasicFileOutput.out",fileContent);
        TextFile text = new TextFile("text.txt");
        text.write("test2.txt");
    }
}
