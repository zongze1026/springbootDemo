package com.zongze.netty.heart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Create By xzz on 2020/4/20
 */
public class NettyServer {

    public static void main(String[] args) {

        //创建两个时间循环组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建服务端启动类，可以配置一系列参数
        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)    //把NioServerSocketChannel作为服务器通道的实现
                    .option(ChannelOption.SO_BACKLOG, 128) //初始化服务器连接队列的大小;高并发下服务器会将处理不过来的链接放入队列
                    .childOption(ChannelOption.SO_KEEPALIVE, true)     //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {      //给workerGroup设置我们自己定义的处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new IdleStateHandler(5,0,0,TimeUnit.SECONDS));
                            socketChannel.pipeline().addLast(new NettyServerTaskHandler());
                        }
                    });

            //绑定端口并启动服务器
            ChannelFuture channelFuture = bootstrap.bind(8888).sync();
            //对关闭通道监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭循环组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
