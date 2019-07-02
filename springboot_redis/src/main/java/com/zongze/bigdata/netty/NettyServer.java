package com.zongze.bigdata.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * Create By xzz on 2019/6/21
 */
public class NettyServer {


    public static void main(String[] args) {
        //创建服务对象
        Bootstrap bootstrap = new Bootstrap();
        //创建两个线程池;用于监听端口号和通讯监听
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();




    }







}
