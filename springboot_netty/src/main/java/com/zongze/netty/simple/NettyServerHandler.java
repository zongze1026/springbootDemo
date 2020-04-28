package com.zongze.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;

/**
 * Create By xzz on 2020/4/21
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * @param msg 就是客户端发送的数据，类型默认为object
     * @param: ctx 上下文对象，通过该对象
     * 可以获取到管道pipeline、通道channel和客户端地址
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx.getClass().toString() + " hash code: " + ctx.hashCode());
        //ByteBuf是netty在nio的ByteBuffer基础上分装的一个对象
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的消息：" + byteBuf.toString(Charset.defaultCharset()));
        System.out.println("客户端的地址：" + ctx.channel().remoteAddress().toString());
    }


    /**
     * 数据读取完毕，响应客户端
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.getClass().toString() + " hash code: " + ctx.hashCode());
        String msg = "你好，客户端";
        //发送数据给客户端需要调用writeAndFlush方法
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(msg, Charset.defaultCharset()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler is added .....");
        super.handlerAdded(ctx);
    }

    /**
     * 出现异常时需要关闭通道
     *
     * @param cause 异常对象，出现异常时该异常对象会注入
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(ctx.getClass().toString() + " hash code: " + ctx.hashCode());
        ctx.channel().close();
    }
}
