package com.zongze.bigdata.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Create By xzz on 2019/5/30
 */
public class NioCopy {


    public static void main(String[] args) {

        String src = "F:\\image\\meinv.jpg";
        String dest = "D:\\test\\meinv.jpg";
        copy(src, dest);

    }


    public static void copy(String src, String dest) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel readChannel = null;
        FileChannel writeChannel = null;
        try {
            inputStream = new FileInputStream(src);
            outputStream = new FileOutputStream(dest);
            readChannel = inputStream.getChannel();
            writeChannel = outputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                int end = readChannel.read(buffer);
                if (end == -1) {
                    return;
                }
                buffer.flip();
                writeChannel.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                readChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writeChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
