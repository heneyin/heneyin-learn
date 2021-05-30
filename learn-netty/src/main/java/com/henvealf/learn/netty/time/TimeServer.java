package com.henvealf.learn.netty.time;


import com.henvealf.learn.netty.common.SimpleSocketServer;

/**
 * @author hongliang.yin/Henvealf on 2018/8/15
 */
public class TimeServer {

    public static void main(String[] args) throws InterruptedException {

        SimpleSocketServer timeServer = new SimpleSocketServer(7077, TimeServerHandler::new);
        timeServer.run();
    }

}
