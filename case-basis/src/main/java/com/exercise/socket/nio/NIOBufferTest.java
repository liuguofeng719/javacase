package com.exercise.socket.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class NIOBufferTest {

    // jdk中对应基本类型都有对应的buffer，boolean除外
    //  mark <= position <= limit <= capacity
    public static void main(String[] args) {
        // 声明一个字节buffer ，容量为10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("初始化byteBuffer");
        println(buffer);
        System.out.println("byteBuffer写入数据，mark、position、limit的变化");
        // buffer 设置值
        buffer.put((byte)'A');
        buffer.put((byte)'B');
        buffer.put((byte)'C');
        println(buffer);

        if (buffer.hasArray()) {
            System.out.println("检查底层是否是数组");
        }

        System.out.println("获取buffer转换成数组 = " + new String(buffer.array()));
        // 切换到读模式
        buffer.flip();
        System.out.println("切换模式之后的，mark、position、limit的变化");
        println(buffer);

        System.out.println("获取buffer的值=" + buffer.get());
        System.out.println("获取buffer的内容，mark、position、limit的变化");
        println(buffer);

        System.out.println("改变buffer position ,limi˙t ");

        //索引是从0开始的，所以这position(2)是C
        // position>=limit java.nio.BufferUnderflowExceptions
        buffer.position(2).limit(3);
        println(buffer);
        System.out.println(buffer.get());
    }

    private static void println(Buffer buffer) {
        StringBuilder sb = new StringBuilder();
        sb.append("mark = " + buffer.mark()).append("\n");
        sb.append("position = " + buffer.position()).append("\n");
        sb.append("limit = " + buffer.limit()).append("\n");
        sb.append("capacity = " + buffer.capacity()).append("\n");
        System.out.println(sb.toString());
    }
}
