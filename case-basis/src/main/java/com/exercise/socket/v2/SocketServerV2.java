package com.exercise.socket.v2;


import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
@Slf4j
public class SocketServerV2 {

    static ScheduledExecutorService executorService =
            new ScheduledThreadPoolExecutor(2, r -> new Thread("xxx"));

    public static void main(String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(8080);
            final Socket socket = serverSocket.accept();
            final InputStream inputStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            while (true) {
                final byte aByte = dis.readByte();
                final int length = dis.readInt();
                byte[] bytes = new byte[length-5];
                dis.readFully(bytes);
//                final String utf = dis.readUTF();
                log.info("数据类型={}，数据长度={},读取的值为{}", aByte,length, new String(bytes));
            }

//            while (true) {
//
//                Runnable runnable = () -> {
//                    try {
//                        final Socket socket = serverSocket.accept();
//                    final InputStream inputStream = socket.getInputStream();
//                    DataInputStream dis = new DataInputStream(inputStream);
//                    while (true) {
//                        final byte aByte = dis.readByte();
//                        final int length = dis.readInt();
//                        byte[] bytes = new byte[length - 5];
//                        dis.readFully(bytes);
//                        log.info("数据类型={}，数据长度={},读取的值为{}", aByte, length, new String(bytes));
//                    }
//                } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                };
//                executorService.submit(runnable);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
