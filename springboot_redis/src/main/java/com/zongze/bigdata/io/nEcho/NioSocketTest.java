package com.zongze.bigdata.io.nEcho;



/**
 * Create By xzz on 2019/6/11
 */
public class NioSocketTest {


    public static void main(String[] args) {
        NioSocketServer nioSocketServer = new NioSocketServer();
        nioSocketServer.initServer(8888);

    }


}
