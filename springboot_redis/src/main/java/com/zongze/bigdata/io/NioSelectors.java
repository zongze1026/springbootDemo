package com.zongze.bigdata.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.*;

/**
 * Create By xzz on 2019/5/30
 */
public class NioSelectors {

    public static void main(String[] args) throws IOException {

//        selector();
        properties();

    }

    private static void selector() {
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
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            SocketChannel clientChannel = server.accept();
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

    public static void properties() throws IOException {
        InputStream resourceAsStream = NioSelectors.class.getClassLoader().getResourceAsStream("123.properties");
        InputStreamReader reader = new InputStreamReader(resourceAsStream,"gbk");
        Properties properties = new Properties();
        properties.load(reader);
        Set<String> strings = properties.stringPropertyNames();
        Map<String,String>data = new HashMap<>();
        strings.stream().forEach(keyProperties->{
            data.put(keyProperties,properties.getProperty(keyProperties));
        });

        for (String mKey:data.keySet()){
            System.out.println(mKey+"="+properties.getProperty(mKey));
        }



    }




}
