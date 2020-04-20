package com.zongze.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Create By xzz on 2020/4/20
 */
public class GroupChatClient {

    private String address = "127.0.0.1";

    private int port = 8888;

    private Selector selector;

    private SocketChannel socketChannel;

    public GroupChatClient() {
        try {
            this.socketChannel = SocketChannel.open(new InetSocketAddress(address, port));
            socketChannel.configureBlocking(false);
            this.selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从客户端读取消息
     */
    public void read() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        clientChannel.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()));
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 发送消息
     */
    public void send(String msg) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        try {
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
