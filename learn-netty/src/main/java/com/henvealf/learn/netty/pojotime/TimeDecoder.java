package com.henvealf.learn.netty.pojotime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author hongliang.yin/Henvealf on 2018/8/16
 */
public class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        // add 之后就以为要发送给下一个channel。
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
