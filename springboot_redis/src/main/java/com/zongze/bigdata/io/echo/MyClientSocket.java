package com.zongze.bigdata.io.echo;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * Create By xzz on 2019/5/31
 */
public class MyClientSocket {


    public static void main(String[] args) throws UnsupportedEncodingException {
        connectServer("客户端第请求");
    }



    private static void connectServer(String message) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            Socket socket = new Socket("192.168.0.149", 8888);
            outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
