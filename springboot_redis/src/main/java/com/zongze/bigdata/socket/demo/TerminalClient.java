package com.zongze.bigdata.socket.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by xieZZ on 2019/5/29
 */
public class TerminalClient {


    public static void main(String[] args) {
        OutputStream ops = null;
        BufferedReader reader = null;
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            ops = socket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                ops.write(line.getBytes("utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ops != null) {
                    ops.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
