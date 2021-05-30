package com.henvealf.learn.java.jvm;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-02-02
 */
public class LearnClassFile implements Closeable {

    private int m;
    private List<String> list;
    private List<String> list1 = new ArrayList<>();

    public static String sta = "ad";

    public LearnClassFile(int cc, String str) {

    }

    public int cal(int a, int b) throws  Exception{
        if (a == 1) {
            throw new Exception("a");
        }
        return a + 1;
    }

    public static int inc() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            System.out.println("i am in finally");
            x = 3;
            return x;
        }
    }

    @Override
    public void close() throws IOException {

    }

    public static void main(String[] args) {
        System.out.println(inc());
    }
}
