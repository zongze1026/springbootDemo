package com.zongze.bigdata.io.nEcho;


import java.io.IOException;

/**
 * Create By xzz on 2019/6/11
 */
public class NioSocketTest {


    public static void main(String[] args) {
        NioSocketServer nioSocketServer = new NioSocketServer();
        try {
            nioSocketServer.initServer(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
