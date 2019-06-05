package com.zongze.bigdata.socket.demo;

import com.alibaba.fastjson.JSON;
import com.zongze.model.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Create By xzz on 2019/5/29
 */
public class ClientSocket {


    public static void main(String[] args) throws IOException {

        //169.254.94.58  192.168.0.116   192.168.246.1


//        Socket socket = new Socket("169.254.94.58",8080);
        Socket socket = new Socket("192.168.0.116",8080);
//        Socket socket = new Socket("192.168.246.1",8080);
        OutputStream outputStream = socket.getOutputStream();
        String hellow ="我爱你哈哈哈helowds哈中国！";
        outputStream.write(hellow.getBytes());
        outputStream.flush();
        outputStream.close();

    }


}
