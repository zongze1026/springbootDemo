package com.zongze.bigdata.io.nEcho;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Create By xzz on 2019/6/11
 */
public class NioClient {

    private static int port = 8888;
    private static String address = "127.0.0.1";

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(address,port));
        socketChannel.configureBlocking(false);
        ByteBuffer buffer =ByteBuffer.wrap(getMessage().getBytes());
        socketChannel.write(buffer);
        buffer.clear();
        socketChannel.close();
    }

    private static String getMessage() {
        return "hellow socketServer";
    }


}
