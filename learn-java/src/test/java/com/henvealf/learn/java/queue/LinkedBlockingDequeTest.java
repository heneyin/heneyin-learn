package com.henvealf.learn.java.queue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author hongliang.yin/Henvealf
 * @date 2021/4/7
 */
public class LinkedBlockingDequeTest {

    @Test
    public void test() {
        LinkedBlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>(10);
        for (int i = 0; i < 11; i++) {
            boolean result = blockingDeque.offer("1");
            System.out.println(i+ ": " + result);
        }
    }

}
