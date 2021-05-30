package com.henvealf.learn.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 该服务会向客户端发送一个 32字节的 整型，不会接收消息中传来的任何数据。
 * 该例子示例了构建并传送一个消息，并且在完成后关闭连接。
 *
 * @author hongliang.yin/Henvealf on 2018/8/3
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当 channel 建立了连接的时候，会触发该函数。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        // 由于 netty 中使用了新的指针以及新的操作类型，而使我们不在用和slip()操作打交道。
        // ChannelFuture 代表一个还没有发生的I/O操作。
        // 因为 netty 中所有的操作都是异步的，所以操作在某些情况下可能不会执行。比如立刻调用 Context 的 close 方法，写入操作就有可能无法正常写入。
        // 为了保证写入完成了才关闭连接，需要在 ChannelFuture 的操作完成监听器中进行连接的关闭。
        final ChannelFuture f = ctx.writeAndFlush(time);

        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                // 参数和上面的 f 指向同一个对象。
                assert future == f;
                // 这里也会返回一个ChannelFuture，所以连接不会立刻被关闭。
                ctx.close();
            }
        });

        // 上面一步的简写。
        // f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
