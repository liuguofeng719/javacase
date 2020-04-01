package com.exercise.socket.demo.nio.channels;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class CopyFile {
    static public void main(String args[]) throws Exception {
        String infile = "src/main/resources/CopyFile.java";
        String outfile = "src/main/resources/CopyFile.java.copy";


        // 从流中获取通道
        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            // 读入之前要清空
            buffer.clear();

            // position自动前进
            int r = fcin.read(buffer);

            if (r == -1) {
                break;
            }

            // position = 0; limit=读到的字节数
            buffer.flip();

            // 从 buffer 中读
            fcout.write(buffer);
        }
    }
}
