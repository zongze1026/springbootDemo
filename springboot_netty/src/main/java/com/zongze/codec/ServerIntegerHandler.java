package com.zongze.codec;

        import io.netty.channel.ChannelHandlerContext;
        import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Create By xzz on 2020/4/23
 */
public class ServerIntegerHandler extends SimpleChannelInboundHandler<Integer> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Integer msg) throws Exception {
        System.out.println("读取到客户端数据=" + msg);
        //响应客户端
        ctx.channel().writeAndFlush(msg + 10);
    }
}
