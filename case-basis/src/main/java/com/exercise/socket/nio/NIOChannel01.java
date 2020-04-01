package com.exercise.socket.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * 通过buffer往通道里面写内容
 **/
public class NIOChannel01 {

    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("3.txt");
        final FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.put((byte) 'A');
        // 切换成读
        buffer.flip();

        channel.write(buffer);
        fileOutputStream.close();
    }
}
