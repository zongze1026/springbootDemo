package com.zongze.bigdata.io.nEcho;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Create By xzz on 2019/6/11
 */
public class NioClient {

    private static int port = 8888;
    private static String address = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress(address, port));
        clientChannel.configureBlocking(false);
        Selector selector = Selector.open();
        clientChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new Sender(clientChannel)).start();
        while (true) {
            selector.select();
            ByteBuffer buffer = ByteBuffer.allocate(2);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while (clientChannel.read(buffer) > 0) {
                buffer.flip();
                out.write(buffer.array(), 0, buffer.limit());
                buffer.clear();
            }
            System.out.println(new String(out.toByteArray()));
        }
    }




}
