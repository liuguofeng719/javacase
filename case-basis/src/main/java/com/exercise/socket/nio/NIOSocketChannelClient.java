package com.exercise.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * BIO 同步阻塞IO
 * NIO 同步非阻塞IO 3大组件，buffer - channel ，channel - buffer 互相读写
 * 1、Channel
 * 2、Buffer
 * 3、Selector 可以注册多个channel，每个channel对应一个SocketChannel（相当于对应一个客户端）
 * 每个channel里面对应各种事件 OP_READ = 1、OP_WRITE = 4、OP_CONNECT = 8、OP_ACCEPT = 16 事件
 * AIO 异步IO
 **/
public class NIOSocketChannelClient {

    public static void main(String[] args) throws Exception {
        // 1、获取SocketChannel
        final SocketChannel socketChannel = SocketChannel.open();
        // 获取Selector
        final Selector selector = Selector.open();
        // 2、设置非阻塞
        socketChannel.configureBlocking(false);
        // 注册读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 3、设置远程连接host和port
        if (!socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888))) {
            while (!socketChannel.finishConnect()) {
                System.out.println("等待连接。。。。。。");
            }
        }
        sendMsg(socketChannel);
        readInfo(selector);
    }

    private static void sendMsg(SocketChannel socketChannel) {
        // 这里不用线程包裹，主线程阻塞，倒是后面的代码不执行
        new Thread(() -> {
            try {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) {
                    final String nextLine = scanner.nextLine();
                    ByteBuffer byteBuffer = ByteBuffer.wrap(nextLine.getBytes());
                    socketChannel.write(byteBuffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private static void readInfo(Selector selector) {
        new Thread(() -> {
            while (true) {
                try {
                    final int select = selector.select();
                    if (select > 0) {
                        final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            final SelectionKey selectionKey = iterator.next();
                            iterator.remove();

                            if (selectionKey.isReadable()) {
                                final SocketChannel channel = (SocketChannel) selectionKey.channel();
                                ByteBuffer buffer = ByteBuffer.allocate(512);
                                while (channel.read(buffer) > 0) {
                                    buffer.flip();
                                    System.out.println("client 读取消息 === " + new String(buffer.array()));
                                    buffer.clear();
                                }
                            }
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
