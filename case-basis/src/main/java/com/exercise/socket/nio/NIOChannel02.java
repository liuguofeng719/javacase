package com.exercise.socket.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * <p>
 * 通过FileChannel把数据读取到另外一个文件
 **/
public class NIOChannel02 {

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("/Users/lgfcxx/Documents/IdeaProject/javacase/case-basis/1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");

        final FileChannel outputStreamChannel = fileOutputStream.getChannel();
        final FileChannel channel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(2);
//        while (channel.read(buffer) != -1) {
        while(true){
            final int read = channel.read(buffer);
            System.out.println("读取的字节数 = " + read);
            if (read == -1) {
                break;
            }
            // 切换读的
            buffer.flip();
            outputStreamChannel.write(buffer);
            // 重置为读
           /* public final Buffer clear() {
                position = 0;
                limit = capacity;
                mark = -1;
                return this;
            }*/
            // 如果不重置，position 一直都是刚刚读取的那个position，去取一直为0，导致死循环
            // 始终会重复读取bytebuffer 数据
            buffer.clear();
        }
        // 关闭流
        fileInputStream.close();
        fileOutputStream.close();
    }
}
