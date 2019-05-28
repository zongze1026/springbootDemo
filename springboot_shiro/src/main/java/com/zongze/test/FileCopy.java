package com.zongze.test;

import java.io.*;

/**
 * Create By xzz on 2019/5/28
 */
public class FileCopy {

    public static void main(String[] args) throws IOException {


//        random();
//        io();
        String srcFile = "";
        String destFile = "";
        File file = new File(srcFile);
        int threads = 4;
        long block = file.length() / threads;
        for (int i = 0; i < threads; i++) {
            int start = 1;
            int end = 2;
            new Thread(new CopyThread(srcFile, destFile, start, end)).start();
        }


    }

    private static void io() throws IOException {
        File file = new File("F:\\image\\123");
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream os = new FileOutputStream("f:\\image\\345");
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fis.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            fis.close();
            os.close();
        } else {
            System.out.println("文件不存在！");
        }
    }

    private static void random() throws IOException {

        RandomAccessFile wirteFile = new RandomAccessFile("F:\\image\\abc.jpg", "rw");
        RandomAccessFile readFile = new RandomAccessFile("F:\\image\\meinv.jpg", "r");
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = readFile.read(buff)) != -1) {
            wirteFile.write(buff, 0, len);
        }
        wirteFile.close();
        readFile.close();

    }


}
