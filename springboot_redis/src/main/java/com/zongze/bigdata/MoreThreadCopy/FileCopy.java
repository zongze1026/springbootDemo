package com.zongze.bigdata.MoreThreadCopy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By xzz on 2019/5/28
 */
public class FileCopy {

    public static void main(String[] args) throws IOException, InterruptedException {
        //343  /199
        long start = System.currentTimeMillis();
        String srcFile = "D:\\学习资料\\大数据\\01.JavaSE+Mysql+JDBC（大数据基础班）\\Java基础第16天\\Java基础第16天-01.作业.avi";
        String destFile = "D:\\test\\abcd.avi";
        List<Thread>list = new ArrayList<>();
        MoreThreadCopy(srcFile, destFile,list);
        for (Thread t:list){
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)/100);


    }

    private static void MoreThreadCopy(String srcFile, String destFile, List<Thread> list) throws InterruptedException {
        File file = new File(srcFile);
        //拷贝线程个数
        int threads = 4;
        //块的大小
        int blockSize = (int) file.length() / threads;
        for (int i = 0; i < threads; i++) {
            int start = i * blockSize;
            int end;
            if (i == threads - 1) {
                end = (int) file.length() - 1;
            } else {
                end = (i + 1) * blockSize - 1;
            }
            Thread thread = new Thread(new CopyThread(srcFile, destFile, start, end));
            thread.start();
            list.add(thread);
        }
    }

    private static void io(String src,String dest) throws IOException {
        File file = new File(src);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream os = new FileOutputStream(dest);
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
