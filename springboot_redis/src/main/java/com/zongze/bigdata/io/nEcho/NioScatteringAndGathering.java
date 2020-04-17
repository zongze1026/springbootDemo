package com.zongze.bigdata.io.nEcho;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Create By xzz on 2020/4/17
 * nio 聚合和分散: 服务端可以将读取到的字节分散到不同的字节数组里面去 ；也可以一致性写入多个字节数组；
 * 其读取或者写入的顺序是按照其数组的顺序写入 如：ByteBuffer[] buffers = new ByteBuffer[2];
 * 客户端：telnet 127.0.0.1 8888  ctrl+ ]
 */
public class NioScatteringAndGathering {


    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8888));

        SocketChannel socketChannel = serverSocketChannel.accept();

        ByteBuffer[] buffers = new ByteBuffer[2];

        buffers[0] = ByteBuffer.allocate(5);
        buffers[1] = ByteBuffer.allocate(5);

        socketChannel.read(buffers);

        System.out.println(new String(buffers[0].array()));
        System.out.println(new String(buffers[1].array()));

        Arrays.asList(buffers).forEach(buffer -> buffer.flip());

        socketChannel.write(buffers);

        socketChannel.close();
        serverSocketChannel.close();


    }


}
