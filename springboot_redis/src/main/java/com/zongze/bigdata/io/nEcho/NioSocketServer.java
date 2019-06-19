package com.zongze.bigdata.io.nEcho;

import java.io.ByteArrayOutputStream;
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

    public void initServer(int port) {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port));
            this.selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        select();
    }

    private void select() {
        while (true) {
            SelectionKey selectionKey = null;
            try {
                while (selector.select() > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        selectionKey = iterator.next();
                        iterator.remove();
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel clientChannel = serverChannel.accept();
                            clientChannel.configureBlocking(false);
                            clientChannel.register(selector, SelectionKey.OP_READ);
                        } else if (selectionKey.isReadable()) {
                            read(selectionKey);
                        }
                    }
                }
            } catch (Exception e) {
                //客户端退出会抛出异常,需要从key集合中移除
                if (selectionKey.channel() instanceof SocketChannel) {
                    selectionKey.cancel();
                    System.out.println("有主机退出了！");
                }
                e.printStackTrace();
            }
        }

    }

    /**
     * 读取客户端的信息
     */
    private void read(SelectionKey selectionKey) throws IOException {
        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((clientChannel.read(buffer))>0){
            outputStream.write(buffer.array(),0,buffer.position());
        }
        String message = new String(outputStream.toByteArray());
        System.out.println("收到客户端的消息:" + message);
        //响应客户端
        buffer.clear();
        buffer.put(("收到客户端的消息:" + message).getBytes());
        buffer.flip();
        clientChannel.write(buffer);
    }


}
