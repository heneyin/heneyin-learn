package com.henvealf.learn.netty.common;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.function.Supplier;

/**
 * @author hongliang.yin/Henvealf on 2018/8/15
 */
public class SimpleSocketClient {

    private final int port;
    private final Supplier<ChannelHandler>[] channelHandlerSuppliers;
    private final String host;

    public SimpleSocketClient(String host, int port, Supplier<ChannelHandler>... channelHandlerSuppliers) {
        this.port = port;
        this.host = host;
        this.channelHandlerSuppliers = channelHandlerSuppliers;
    }

    public void run() throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 和 ServerBootstrap 相似，不同的是他是为非服务的渠道准备的，比如客户端或者非链接channel。
            Bootstrap b = new Bootstrap();
            // 只选了一个 EventLoopGroup，该 EventLoopGroup 即会作为 boss，也会作为 worker。
            b.group(workerGroup);
            // NioSocketChannel 是用作创建一个客户端的Channel。
            b.channel(NioSocketChannel.class);
            //  the client-side SocketChannel does not have a parent.
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    for (Supplier<ChannelHandler> supplier : channelHandlerSuppliers) {
                        pipeline.addLast(supplier.get());
                    }
                }
            });
            // connect 而不是 bind。
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
