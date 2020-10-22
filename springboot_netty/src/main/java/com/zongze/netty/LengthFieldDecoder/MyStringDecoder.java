package com.zongze.netty.LengthFieldDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Date 2020/10/21 21:37
 * @Created by xzz
 */
public class MyStringDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] length = new byte[4];
        in.readBytes(length);
        System.out.println("读取到的消息长度是" + bytes2int(length));
        out.add(in.toString(Charset.forName("utf-8")));
    }

    private void aa(ByteBuf in, List<Object> out) {
        in.markReaderIndex();
        byte[] length = new byte[4];
        in.readBytes(length);
        int i = bytes2int(length);
        if(in.readableBytes()>=i){
            System.out.println("读取到的消息长度是" + bytes2int(length));
            out.add(in.toString(Charset.forName("utf-8")));
        }else{
            in.resetReaderIndex();
        }
    }

    public static int bytes2int(byte[] bytes) {
        return (bytes[0] << 24) | ((bytes[1] & 0xff) << 16) | ((bytes[2] & 0xff) << 8) | (bytes[3] & 0xff);
    }
}
