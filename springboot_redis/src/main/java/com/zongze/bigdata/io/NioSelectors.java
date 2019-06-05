package com.zongze.bigdata.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Create By xzz on 2019/5/30
 */
public class NioSelectors {

    public static void main(String[] args) {

        try {
            Selector selector = Selector.open();
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(8888));
            channel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int select = selector.select();
                if (select > 0) {
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                            System.out.println("好好学习");
                            ServerSocketChannel clientChannel = (ServerSocketChannel) key.channel();
                        } else if ((key.readyOps() & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT) {
                            System.out.println("有新的链接进来了");
                        } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                            System.out.println("读操作已经准备好了");
                        } else if ((key.readyOps() & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE) {
                            System.out.println("写操作已经准备好了");
                        }
                        keyIterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
