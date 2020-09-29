package com.zongze.netty.http;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.Set;

/**
 * Create By xzz on 2020/4/21
 * 1.{@link SimpleChannelInboundHandler}相对于{@link ChannelInboundHandlerAdapter}来说有一个好处就是会自动帮你释放
 * 资源，而不需要手动释放；如果直接使用ChannelInboundHandlerAdapter的话，如果不手动释放资源的话可能会导致
 * 内存异常
 * 2.SimpleChannelInboundHandler是怎么实现自动释放资源的呢？可以发现该类也是继承了ChannelInboundHandlerAdapter
 *  它是通过重写了channelRead方法中释放资源的;并且调用了扩展方法channelRead0(该方法就是业务逻辑需要实现的方法)
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
        if (msg instanceof FullHttpRequest) {
            //获取http请求的uri，可以根据不同的uri执行不同的业务
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            String content = httpRequest.content().toString(Charset.forName("utf-8"));
            System.out.println(content);
            //获取服务器图标请求就拦截
            String uri = httpRequest.uri();
            System.out.println(uri);
            if ("/favicon.ico".equals(uri)) {
                return;
            }
            Set<String> names = httpRequest.headers().names();
            names.stream().forEach(httpHeaderName-> System.out.println(httpHeaderName));
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
