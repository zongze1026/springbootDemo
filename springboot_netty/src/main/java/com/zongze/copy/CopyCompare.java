package com.zongze.copy;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Create By xzz on 2020/4/20
 *
 * 普通拷贝与零拷贝性能测试
 */
public class CopyCompare {

    public static void main(String[] args) {

        String source = "C:\\Users\\Administrator\\Desktop\\image2\\aa.iso";

        String target = "C:\\Users\\Administrator\\Desktop\\image2\\bb.pdf";
        String target2 = "C:\\Users\\Administrator\\Desktop\\image2\\cc.iso";
//        generalCopy(source, target);

        zeroCopy(source, target2);


    }

    /**
     * 零拷贝实现
     * 据说零拷贝在windows系统一次性只能够传8m，linux下可以无限大
     */
    private static void zeroCopy(String source, String target) {
        long start = System.currentTimeMillis();
        try {
            RandomAccessFile sourceFile = new RandomAccessFile(source, "rw");
            RandomAccessFile targetFile = new RandomAccessFile(target, "rw");
            FileChannel sourceChannel = sourceFile.getChannel();
            FileChannel targetChannel = targetFile.getChannel();
            sourceChannel.transferTo(0, sourceFile.length(), targetChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("零copy花费的时间：" + (System.currentTimeMillis() - start));
    }


    private static void generalCopy(String source, String target) {
        long start = System.currentTimeMillis();
        try {
            RandomAccessFile sourceFile = new RandomAccessFile(source, "rw");
            RandomAccessFile targetFile = new RandomAccessFile(target, "rw");
            FileChannel sourceChannel = sourceFile.getChannel();
            FileChannel targetChannel = targetFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            while (sourceChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                targetChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            sourceChannel.close();
            targetChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("普通copy花费的时间：" + (System.currentTimeMillis() - start));
    }


}
