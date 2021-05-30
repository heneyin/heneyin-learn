package com.henvealf.learn.netty.time;

import com.henvealf.learn.netty.common.SimpleSocketClient;

/**
 * @author hongliang.yin/Henvealf on 2018/8/16
 */
public class TimeClient {

    public static void main(String[] args) throws InterruptedException {
        SimpleSocketClient timeClient = new SimpleSocketClient("localhost", 7077, TimeClientHandler::new);
        timeClient.run();
    }
}
