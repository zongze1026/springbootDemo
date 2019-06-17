package com.zongze.bigdata.io.nEcho;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Create By xzz on 2019/6/11\
 * noi server
 */
public class NioSocketServer {

    private Selector selector;

    public void initServer(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        this.selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        select();
    }

    private void select() throws IOException {
        while (true) {
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel clientChannel = serverChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    } else if (selectionKey.isReadable()) {
                        read(selectionKey);
                    }
                }
            }
        }

    }

    /**
     * 读取客户端的信息
     */
    private void read(SelectionKey selectionKey) throws IOException {
        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
        clientChannel.read(buffer);
        String message = new String(buffer.array());
        System.out.println("收到客户端的消息:"+message);
        buffer.clear();
        clientChannel.close();
    }


}
