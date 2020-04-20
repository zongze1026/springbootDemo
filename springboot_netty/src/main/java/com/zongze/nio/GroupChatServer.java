package com.zongze.nio;

import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Create By xzz on 2020/4/20
 */
public class GroupChatServer {

    private int port = 8888;

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    public GroupChatServer() {
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            this.selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        SelectionKey selectionKey = null;
        SocketChannel clientChannel = null;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, selectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        clientChannel = (SocketChannel) selectionKey.channel();
                        handlerRead(clientChannel);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            try {
                if (!ObjectUtils.isEmpty(selectionKey)) {
                    selectionKey.cancel();
                }
                if (!ObjectUtils.isEmpty(clientChannel)) {
                    clientChannel.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 读取客户端消息
     */
    private void handlerRead(SocketChannel socketChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);
        String msg = new String(byteBuffer.array());
        System.out.println(msg);
        scatterMessage(msg, socketChannel);
    }


    /**
     * 转发其他的客户端
     */
    private void scatterMessage(String msg, SocketChannel socketChannel) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            SelectableChannel channel = key.channel();
            if (channel instanceof SocketChannel && socketChannel != channel) {
                ((SocketChannel) channel).write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }


}
