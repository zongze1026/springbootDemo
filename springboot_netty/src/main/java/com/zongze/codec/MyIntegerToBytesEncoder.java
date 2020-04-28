package com.zongze.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Create By xzz on 2020/4/23
 * 自定义一个int转换成byte[]数组的编码器
 * 1.继承MessageToByteEncoder泛型里指定需要转换的类型
 */
public class MyIntegerToBytesEncoder extends MessageToByteEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) throws Exception {
        System.out.println("integer编码器被调用");
        out.writeInt(msg);
    }
}
