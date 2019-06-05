package com.zongze.bigdata.io.MoreThreadCopy;
import java.io.RandomAccessFile;

/**
 * Create By xzz on 2019/5/28
 */
public class CopyThread implements Runnable {
    //源文件
    private String srcFile;
    //目标文件
    private String destFile;
    //起始坐标
    private int start;
    //结束坐标
    private int end;

    public CopyThread(String srcFile, String destFile, int start, int end) {
        this.srcFile = srcFile;
        this.destFile = destFile;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"开始拷贝！");
            RandomAccessFile read = new RandomAccessFile(srcFile, "r");
            RandomAccessFile write = new RandomAccessFile(destFile, "rw");
            read.seek(start);
            write.seek(start);
            //计算每一段的总字节数
            int size = end - start + 1;
            byte[] buf = new byte[1024];
            int mod = size % buf.length;
            int count; //需要循环的次数
            if (mod == 0) {
                count = size / buf.length;
            } else {
                count = size / buf.length + 1;
            }
            for (int i = 0; i < count; i++) {
                //是否是最后一次读取
                if (i == count - 1) {
                    read.read(buf, 0, size % buf.length == 0 ? buf.length : mod);
                    write.write(buf, 0, size % buf.length == 0 ? buf.length : mod);
                } else {
                    read.read(buf);
                    write.write(buf);
                }
            }
            read.close();
            write.close();
            System.out.println(Thread.currentThread().getName()+"拷贝完成");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
