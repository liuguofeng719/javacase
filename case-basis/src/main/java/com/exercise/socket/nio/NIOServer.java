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
 **/
public class NIOServer {

    public static void main(String[] args) {

        try {
            new Thread(new MyServer()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyServer implements Runnable {
        private ServerSocketChannel socketChannel = null;
        private Selector selector = null;
        private volatile boolean isStarted = false;

        public MyServer() {
            try {
                // 创建Selector
                selector = Selector.open();
                socketChannel = ServerSocketChannel.open();
                socketChannel.bind(new InetSocketAddress(8081));
                // 设置非阻塞
                socketChannel.configureBlocking(false);
                // 注册accept事件 ,监听客服端和服务器端是否连接
                socketChannel.register(selector, SelectionKey.OP_ACCEPT);
                isStarted = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            // 遍历selector
            while (isStarted) {
                try {
                    // 该方法阻塞，每1s来唤醒来检查有没有读写事件
                    selector.select(1000);
                    // 遍历已经准备好的事件
                    final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        final SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        handlerInput(selectionKey);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handlerInput(SelectionKey selectionKey) throws IOException {
            // 建立连接的请求
            if (selectionKey.isAcceptable()) {
                final ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                // //完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                final SocketChannel sc = channel.accept();
                // 设置非阻塞
                sc.configureBlocking(false);
                // 注册读事件
                sc.register(selector, SelectionKey.OP_READ);
            }

            // 读请求
            if (selectionKey.isReadable()) {
                final SocketChannel sc = (SocketChannel) selectionKey.channel();
                // 分配1m推外内存
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                final int read = sc.read(buffer);
                if (read > 0) {
                    // 切换成读模式
                    buffer.flip();
                    // 读取数据
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);

                    final String request = new String(bytes, "UTF-8");
                    System.out.println("=======server get requst = " + request);

                    // 把消息到发送client
                    doSendClient(sc, request);
                } else {
                    selectionKey.cancel();
                    sc.close();
                }
            }

        }

        private void doSendClient(SocketChannel sc, String request) throws IOException {
            final byte[] bytes = ("非常不错哦=" + request).getBytes();
            ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
            buffer.put(bytes);
            // 读模式
            buffer.flip();
            sc.write(buffer);
            //****此处不含处理“写半包”的代码
        }
    }
}
