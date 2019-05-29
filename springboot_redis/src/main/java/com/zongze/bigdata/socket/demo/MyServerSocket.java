package com.zongze.bigdata.socket.demo;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create By xzz on 2019/5/29
 * socket基础
 * 创建服务端socket
 */
public class MyServerSocket {


    public static void main(String[] args) throws IOException {
        /**
         * 创建socket两种方法
         * 1、指定ip创建;服务端只能通过该网卡的ip访问
         * 2、创建通用socket;所有的网卡都可以访问
         */
        //第一种：指定ip创建
//        InetAddress inet1 = InetAddress.getByName("192.168.0.149");
//        ServerSocket serverSocket = new ServerSocket(8080, 20, inet1);
        //第二种:通过0.0.0.0通配符创建
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            InputStreamReader reader = null;
            try {
                Socket socket = serverSocket.accept();
                //获取远程客户端主机地址
                System.out.println("收到一个连接！");
                InetSocketAddress address = (InetSocketAddress) socket.getRemoteSocketAddress();
                //客户端主机和端口
                System.out.println(address.getHostName() + ":" + address.getPort());
                InputStream inputStream = socket.getInputStream();
                reader = new InputStreamReader(inputStream, "utf-8");
                char[] buff = new char[1024];
                int len;
                while ((len = reader.read(buff)) != -1) {
                    System.out.println(new String(buff, 0, len));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }
        }


    }


}
