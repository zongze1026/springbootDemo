package com.zongze.bigdata.io;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Create By xzz on 2019/5/30
 * buffer相关api
 */
public class ApiBuffer {

    public static void main(String[] args) throws IOException {
        /**
         *在堆内存中开辟一块指定大小的内存
         */
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        /**
         * position buffer中读写的起始位置,新建的buffer起始位置为0
         */
        buffer.position();

        /**
         *limit buffer中读写的最大位置,新建的limit大小为指定的值
         */
        buffer.limit();

        /**
         * 数据写入到缓冲区结束后调用改方法
         * 该方法会将position置为0;同时将limit置为position位置
         * 此时limit表示有多少可以读取的字节
         */
        buffer.flip();

        /**
         * 将position置为0;可以重新读取数据，limit的位置不变
         */
        buffer.rewind();

        /**
         * 清除buffer的数据;回复到刚新建buffer的状态，limit置为capacity位置
         */
        buffer.clear();

        /**
         *该方法类似clear方法；该方法不会清除所有的数据
         * 将未读取的数据copy到起始位置
         * position的位置在未读取的数据的最后面的位置
         * limit的位置为capacity位置
         */
        buffer.compact();

        /**
         *读取过程中可以标记position位置
         * 可以通过reset方法使position的位置重新到mark的位置
         */
        buffer.mark();
        buffer.reset();

        buffer.hasRemaining();

    }


}
