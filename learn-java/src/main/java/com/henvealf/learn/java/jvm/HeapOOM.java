package com.henvealf.learn.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * mock OutOfMemoryError
 *
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author hongliang.yin/Henvealf on 2018/11/4
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }

}
