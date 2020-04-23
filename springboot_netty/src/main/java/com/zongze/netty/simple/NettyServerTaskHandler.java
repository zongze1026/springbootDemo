package com.zongze.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;

/**
 * Create By xzz on 2020/4/21
 * 自定义任务添加到NioEvenLoop的任务队列中去
 * 1. ctx.channel().eventLoop().execute 提交一个 普通任务
 * 2. ctx.channel().eventLoop().schedule 提交一个延时任务
 * <p>
 * 使用场景：服务端需要耗时的业务操作时，但是需要尽快返回结果给客户端
 * 而不是阻塞到任务处理完成后才返回
 */
public class NettyServerTaskHandler extends ChannelInboundHandlerAdapter {


    //假设有一个非常耗时的操作，我们需要尽快给客户端返回的话，那我们就可以
    //自己定义一个任务添加到任务队列中即可:
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
        //输出当前线程
        System.out.println(Thread.currentThread().getName());
        //需要进行业务处理，假设非常耗时的操作需要5秒钟，但是我们需要尽快回复客户端
        //1.从上下文中获取到eventLoop，并提交一个任务到队列
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //业务处理需要5秒
                    Thread.sleep(5000);
                    //输出当前线程，可以发现该线程和提交任务的线程是同一个线程
                    System.out.println(Thread.currentThread().getName());
                    ctx.channel().writeAndFlush(Unpooled.copiedBuffer("任务处理完毕", Charset.defaultCharset()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //2.回复客户端
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("任务已经提交", Charset.defaultCharset()));
    }


}
