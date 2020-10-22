package com.zongze.netty.LengthFieldDecoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Create By xzz on 2020/4/21
 */
public class nettyChatChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(2048,0,4,0,4));
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(2048,0,4));
        ch.pipeline().addLast(new MyStringDecoder());
        ch.pipeline().addLast(new MyHandler());
    }
}
