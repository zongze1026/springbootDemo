package com.zongze.bigdata.io;

import com.zongze.model.User;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Create By xzz on 2019/5/30
 */
public class NioSelectors {

    public static void main(String[] args) {

        try {
            Selector selector = Selector.open();
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
