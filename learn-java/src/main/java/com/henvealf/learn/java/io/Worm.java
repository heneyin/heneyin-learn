package com.henvealf.learn.java.io;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.util.Random;

/**
 * 通过链接对象生成一个worm蠕虫，对序列化机制进行测试。
 * <p>每个对象都与worm中的下一个worm链接，同时又与属于不同类Data对象的引用数组链接。
 * <p>Created by Henvealf on 2016/9/6.
 */

class Data implements Serializable {
    private int n;
    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
}

public class Worm implements Serializable{
    private static Random rand = new Random(47);
    //三个Data对象
    private Data[] d = {
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)),
    };

    private Worm next;

    private char c;

    // 一个递归，建立Worm之间的链接
    // 6 --> 5 --> 4 --> 3 --> 2 --> 1
    public Worm(int i, char x) {
        System.out.println("Worm constructor: " + i);
        c = x;
        if(--i > 0)
            next = new Worm(i, (char)(x + 1));
    }

    public Worm() {
        System.out.println("Default constructor");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for(Data dat : d)
            result.append(dat);
        result.append(")");
        if(next != null)
            result.append(next);
        return result.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Worm w = new Worm(6,'a');

        System.out.println("w = " + w);

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("worm.out")
        );

        out.writeObject("Worm storage\n");

        out.writeObject(w);

        out.close();    //也同时刷新输出流
        System.out.println("---------------------------");
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("worm.out")
        );
        String s = (String)in.readObject();
        Worm w2 = (Worm) in.readObject();
        System.out.println(s + "w2 = " + w2);

        //以内存缓冲区作为输出流
        ByteArrayOutputStream bout =
                new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeObject("Worm storage");
        out2.writeObject(w);
        out2.flush();
        ObjectInputStream in2 = new ObjectInputStream(
                new ByteArrayInputStream(bout.toByteArray())
        );
        s = (String) in2.readObject();
        Worm w3 = (Worm)in2.readObject();
        System.out.println(s + " w3 = " + w3);
    }
}
