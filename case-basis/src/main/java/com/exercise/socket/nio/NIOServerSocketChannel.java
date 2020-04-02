package com.exercise.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
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
public class NIOServerSocketChannel {

    public static void main(String[] args) throws Exception {
        // 1、获取服务器连接通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2、获取selector
        Selector selector = Selector.open();
        // 3、开辟端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        // 4、设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 5、ServerSocketChannel注册accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                // select()阻塞方法、select(timeout) 非阻塞方法
                final int select = selector.select();
                if (select > 0) {
                    // 获取所有的方法事件的SelectionKey
                    final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    System.out.println("获取触发事件的socketChannel = " + selectionKeys.size());

                    while (iterator.hasNext()) {
                        final SelectionKey key = iterator.next();
                        // 移除对应的事件，防止重复触发
                        iterator.remove();
                        // 通过SelectionKey 找到对应的订阅事件的通道
                        // 如果accept事件，获取SocketChannel并且注册读事件
                        if (key.isAcceptable()) {
                            final ServerSocketChannel scc = (ServerSocketChannel) key.channel();
                            // 注册客户端的读取请求
                            final SocketChannel channel = scc.accept();
                            // 服务端设置了阻塞，SocketChannel通道必须设置非阻塞
                            channel.configureBlocking(false);
//                            channel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(10));
                            channel.register(selector, SelectionKey.OP_READ);
                            System.out.println("客户端连接的个数， 客户端注册的socketChannel = " + selector.keys().size());
                            System.out.println("远程连接地址： " + channel.getRemoteAddress());
                        }

                        if (key.isReadable()) {
                            try {
                                // 获取初始通道带的ByteBuffer
//                                final ByteBuffer buffer = (ByteBuffer) key.attachment();
                                // 获取事件对应的SocketChannel通道
                                final SocketChannel socketChannel = (SocketChannel) key.channel();
                                // 把通道的数据缓冲buffer里面
                                ByteBuffer buffer = ByteBuffer.allocate(512);
                                final int read = socketChannel.read(buffer);
                                if (read > 0) {
                                    System.out.println("读取的字节数 = " + read + " 内容 = " + new String(buffer.array()));
                                    socketChannel.write(ByteBuffer.wrap("开车了".getBytes()));
                                }
                            } catch (IOException e) {
                                key.cancel();
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
