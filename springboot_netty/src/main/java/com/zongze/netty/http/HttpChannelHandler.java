package com.zongze.netty.http;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Create By xzz on 2020/4/21
 */
public class HttpChannelHandler extends SimpleChannelInboundHandler<HttpObject> {


    /**
     * @param msg 该参数是对客户端和服务器端通讯的数据封装，封装成一个HttpObject类型的对象
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //响应给客户端的消息
        String message = JSON.toJSONString(new Order(12, "羽绒服", 288.00));
        ByteBuf byteBuf = Unpooled.copiedBuffer(message, CharsetUtil.UTF_8);
        //判断请求是否是http请求
        if (msg instanceof HttpRequest) {
            //获取http请求的uri，可以根据不同的uri执行不同的业务
            HttpRequest httpRequest = (HttpRequest) msg;
            //获取服务器图标请求就拦截
            if ("/favicon.ico".equals(httpRequest.uri())) {
                return;
            }
            System.out.println("pipeline哈希：" + ctx.pipeline().hashCode() + " channel哈希：" + ctx.channel().hashCode());
            //指定http版本、响应状态、响应内容
            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            //指定响应类型
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            ctx.writeAndFlush(httpResponse);
        }


    }
}
