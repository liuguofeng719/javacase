package com.exercise.socket.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 *
 * 把src复制到target中
 * target.transferFrom(ReadableByteChannel src,long position, long count)
 * 把src 复制到target中
 * src.transferTo(long position, long count,WritableByteChannel target)
 *
 **/
public class NIOChannel03 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("4.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("5.txt");

        final FileChannel src = fileInputStream.getChannel();
        final FileChannel dst = fileOutputStream.getChannel();

        // src 读取到dst 中，从0开始，count 代表每次读取多少个字节
        System.out.println( "文件类容大小 = " + src.size());
        dst.transferFrom(src,0,src.size());
        src.close();
        dst.close();
        fileInputStream.close();
        fileInputStream.close();
    }
}
