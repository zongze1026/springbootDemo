package com.zongze.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Create By xzz on 2020/4/23
 * 自定义一个byte[]转换成int的解码器
 * 1.继承ByteToMessageDecoder并重写方法
 * 2.自定义实现逻辑
 */
public class MyByteToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //1.一个int等于4个字节，所以需要判断ByteBuf是否有足够的字节可读
        //2.从ByteBuf中读取的数据添加到List<Object> out集合中，它会传递到下一个channelHandler
        System.out.println("integer解码器被调用");
        if (in.readableBytes() >= 4) {
            out.add(in.readInt());
        }
    }
}
