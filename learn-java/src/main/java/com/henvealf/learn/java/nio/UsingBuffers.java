package com.henvealf.learn.java.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * 1.
 * 在缓冲区里面插入和提取数据的时候会更新ByteBuffer中的四个索引，
 * 这里使用一个小程序来体现他们的变化。
 * 交换相邻字符
 * Created by Henvealf on 2016/9/4.
 */
public class UsingBuffers {

    private static void symmetricScramble(CharBuffer charBuffer) {
        //hasRemaining,判断在position和limit之间还有没有元素。
        while(charBuffer.hasRemaining()) {
            //标记一下
            charBuffer.mark();
            char c1 = charBuffer.get();
            char c2 = charBuffer.get();
            //将指针position设置为刚才mark的位置。
            charBuffer.reset();
            charBuffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args){

        char[] data = "UsingBuffers".toCharArray();

        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);

        CharBuffer cb = bb.asCharBuffer();

        cb.put(data);

        System.out.println(cb.rewind());

        symmetricScramble(cb);
        System.out.println(cb.rewind());

        symmetricScramble(cb);
        System.out.println(cb.rewind());
        System.out.println("你好");
    }
}
