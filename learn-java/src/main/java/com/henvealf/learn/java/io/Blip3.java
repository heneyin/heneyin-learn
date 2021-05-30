package com.henvealf.learn.java.io;

import java.io.*;

/**
 * 如果你想定制序列化操作，希望对象恢复时按照定制的样子恢复，就可以使用 Externalizable
 * <p>存储(序列化)时的顺序：
 * <br> 1. 先调用类的默认的构造器，如果构造器不是public，将会抛出异常。
 * <br> 2. 然后调用writeExternal()方法。
 * <br>
 *     <br>
 * 恢复(反序列化)时的顺序为：
 * <br>  1. 先调用类的默认的构造器，如果构造器不是public，将会抛出异常。
 * <br>  2. 然后调用readExternal()方法。
 *
 * <br> 注意：如果我们从一个Externalizabled对象继承，通常需要调用基类的writeExternal()和readExternal().
 *  Created by Henvealf on 2016/9/6.
 */
public class Blip3 implements Externalizable {

    private int i;
    private String s;
    private double d;
    public Blip3() {
        System.out.println("Blip3 Constructor");
    }

    public Blip3(String x, int a, double d) {
        System.out.println("Blip3(String x, int a)");
        s = x;
        i = a;
        this.d = d;
    }

    public String toString() {
        return s +" " + i + " " + d;
    }

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        out.writeObject(s);
        //序列化写入的时候并没有写入double
        out.writeInt(i);
    }

    /**
     * 反序列化
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructor objects :");
        Blip3 b3 = new Blip3("A string", 47,0.66);
        System.out.println(b3);

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("Blip3.out")
        );
        System.out.println("Save object");
        out.writeObject(b3);
        out.close();

        //现在反序列化
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Blip3.out")
        );

        System.out.println("Recovering b3");
        b3 = (Blip3) in.readObject();
        //读出时候发现double的值没有保留下来。
        System.out.println(b3);
    }
}
