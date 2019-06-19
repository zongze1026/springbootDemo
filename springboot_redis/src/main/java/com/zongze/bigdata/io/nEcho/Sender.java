package com.zongze.bigdata.io.nEcho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Create By xzz on 2019/6/19
 */
public class Sender implements Runnable {
    private SocketChannel clientChannel;

    public Sender(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            ByteBuffer buffer = null;
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer = ByteBuffer.allocate(1024);
                buffer.put(line.getBytes());
                buffer.flip();
                clientChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
