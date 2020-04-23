package com.zongze.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Create By xzz on 2020/4/22
 */
public class NettyChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * @channelGroup 维护了客户端通道的实例;当通道断开链接会自动移除
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("================");
        scatterMsg(ctx, msg);
    }


    /**
     * channel处于活跃状态
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    /**
     * channel处于断开状态
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


    /**
     * 客户端与服务器一旦建立连接，就会被执行;可以通过该方法把
     * channel添加到channelGroup中去
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
        scatterMsg(ctx, "用户【" + ctx.channel().remoteAddress().toString().substring(1) + "】上线了！");
    }

    /**
     * 移除handler时被调用
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        scatterMsg(ctx, "用户【" + ctx.channel().remoteAddress().toString().substring(1) + "】下线了！");
    }


    private void scatterMsg(ChannelHandlerContext ctx, String msg) {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush(msg);
            }
        });
    }

}
