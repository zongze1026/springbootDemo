package com.zongze.netty.heart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Create By xzz on 2020/4/21
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {


    //当通道准备就绪的时候就会触发该方法；然后就可以将数据写入通道
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "请求提交任务";
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    //当通道中有数据可读的时候触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("客户端检测心跳");
        super.userEventTriggered(ctx, evt);
    }
}
