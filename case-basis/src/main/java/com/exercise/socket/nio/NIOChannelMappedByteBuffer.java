package com.exercise.socket.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class NIOChannelMappedByteBuffer {

    public static void main(String[] args) throws Exception {

        RandomAccessFile accessFile = new RandomAccessFile("4.txt", "rw");
        final FileChannel channel = accessFile.getChannel();

        // DirectByteBuffer
        final MappedByteBuffer mappedByteBuffer =
                // 1.读写模式
                // 2.映射的区域从0开始读写
                // 3.映射多少个字节,这里5个字节,要映射的区域的大小
                channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'A');
        mappedByteBuffer.put(3, (byte) 'Z');

        // hello,world,映射到内存中的是hello
        // mappedByteBuffer.put(5, (byte) 'Z');
        // 修改第5个会出java.lang.IndexOutOfBoundsException
        while (mappedByteBuffer.hasRemaining()) {
            System.out.println(mappedByteBuffer.get());
        }

        accessFile.close();
    }
}
