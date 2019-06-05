package com.zongze.bigdata.io.echo;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Create By xzz on 2019/5/31
 */
public class SocketWorker implements Runnable {
    private Socket socket;

    public SocketWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        InputStreamReader reader = null;
        OutputStreamWriter writer = null;

        InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
        String hostAddress = socketAddress.getAddress().getHostAddress();
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            reader = new InputStreamReader(in, "utf-8");
            writer = new OutputStreamWriter(out, "utf-8");
            char[] buff = new char[100];
            int len = 0;
            while ((len = reader.read(buff)) != -1) {
                System.out.println("收到主机：" + hostAddress + "的消息" + new String(buff, 0, len));
                writer.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
