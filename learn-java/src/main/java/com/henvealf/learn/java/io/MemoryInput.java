package com.henvealf.learn.java.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * 从内存输入
 * Created by Henvealf on 2016/9/2.
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(
            BufferedInputFile.read("BufferedInputTest.in")
            //"123456677\ndtrdtyd"
            );
        int  c;
        //StringReader read()返回的是char的int类型 ，Unicode编码？看来是的。
        while ((c = in.read())!= -1){
            System.out.print((char)c);
            //System.out.println(c);
        }
    }
}
