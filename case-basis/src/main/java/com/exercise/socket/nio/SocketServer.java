package com.exercise.socket.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * 1、单线程，每次只能支持一个客户端连接
 * 2、accept 方法和read 方法为阻塞方法
 **/
public class SocketServer {
    public static void main(String[] args) throws Exception {
        // telnet 127.0.0.1 8888
        ServerSocket serverSocket = new ServerSocket(8888);
        // 增加多线程，去掉while就是单线程，多线程占用资源，引用线程池也不能满足成千上w的app连接
        while(true) {
            System.out.println("开始等待连接中。。。。");
            // 该方法阻塞
            final Socket accept = serverSocket.accept();
            System.out.println("连接成功");
            new Thread() {
                @Override
                public void run() {
                    try {
                        final InputStream inputStream = accept.getInputStream();
                        while (true) {
                            byte[] bytes = new byte[2];
                            System.out.println("开始等待读取数据");
                            // 方法阻塞，如果超过2个字节需要读取2次
                            final int read = inputStream.read(bytes);
                            System.out.println("每次读取的字节数 = " + read);
                            System.out.println(new String(bytes));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
