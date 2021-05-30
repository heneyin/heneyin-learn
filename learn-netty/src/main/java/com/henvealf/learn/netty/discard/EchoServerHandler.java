package com.henvealf.learn.netty.discard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ECHO 类型的server， 将服务端接受到的数据写回到客户端。
 * @author hongliang.yin/Henvealf on 2018/8/3
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.writeAndFlush(msg);
    }
}
