package com.zongze.netty.LengthFieldDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Date 2020/10/21 20:33
 * @Created by xzz
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===================");
        String msg1 = "这是消息1的测试";
        String msg2 = "这是消息2的测试";
        byte[] b1 = msg1.getBytes();
        byte[] b2 = msg2.getBytes();
        ByteBuf byteBuf = Unpooled.copiedBuffer(int2bytes(b1.length), b1,int2bytes(b2.length),b2);
        ctx.writeAndFlush(byteBuf);
    }


    public static byte[] int2bytes(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i >> 24);
        bytes[1] = (byte) (i >> 16);
        bytes[2] = (byte) (i >> 8);
        bytes[3] = (byte) (i >> 0);
        return bytes;
    }

}
