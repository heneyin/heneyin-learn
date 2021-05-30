package com.henvealf.learn.netty.discard;

import com.henvealf.learn.netty.common.SimpleSocketServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author hongliang.yin/Henvealf on 2018/8/1
 */
public class DiscardServer {

    public static void main(String[] args) throws InterruptedException {
        int port = 9099;
        new SimpleSocketServer(port, DiscardServerHandler::new).run();
    }
}
