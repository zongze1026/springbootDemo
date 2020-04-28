package com.zongze.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Create By xzz on 2020/4/23
 */
public class ClientIntegerHandler extends SimpleChannelInboundHandler<Integer> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Integer msg) throws Exception {
        System.out.println("读取到服务器的数据=" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(25);
    }
}
