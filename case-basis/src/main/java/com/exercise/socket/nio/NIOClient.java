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
 **/
public class NIOClient {

    public static void main(String[] args) throws IOException {
        final ClientHandler target = new ClientHandler("127.0.0.1", 8081);
        new Thread(target).start();
        while (target.sendMsg(new Scanner(System.in).nextLine())) {

        }
    }
}

class ClientHandler implements Runnable {

    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean isStarted;
    private String host;
    private int port;

    public ClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            // 创建selector
            selector = Selector.open();
            // 打开监听通道
            socketChannel = SocketChannel.open();
            // 设置非阻塞
            socketChannel.configureBlocking(false);
            isStarted = true;
            System.out.println("client is started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("running....");
            // 绑定端口，如果失败，重新注册连接事件
            this.doConnect();

            while (isStarted) {
//                selector.select(1000);
                // 非阻塞
                final int selectNow = selector.selectNow();
                if (selectNow == 0) continue;
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey selectionKey = null;
                while (iterator.hasNext()) {
                    selectionKey = iterator.next();
                    iterator.remove();
                    handlerInput(selectionKey);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            System.out.println("connect");
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private void handlerInput(SelectionKey selectionKey) throws IOException {

        if (selectionKey.isValid()) {

            final SocketChannel sc = (SocketChannel) selectionKey.channel();
            // 检查是连接是否可用
            if (selectionKey.isConnectable()) {
                // 检查是否完成连接
                while (sc.finishConnect()) {
                    System.out.println("finish");
                    break;
                }
            }

            if (selectionKey.isReadable()) {
                //
                final ByteBuffer dst = ByteBuffer.allocateDirect(1024);
                final int read = sc.read(dst);
                if (read > 0) {
                    // 翻转操作
                    dst.flip();
                    final byte[] bytes = new byte[dst.remaining()];
                    dst.get(bytes);
                    String data = new String(bytes, "UTF-8");
                    System.out.println(" get server result " + data);
                    //没有读取到字节 忽略
//				else if(readBytes==0);
                    //链路已经关闭，释放资源
                } else if (read < 0) {
                    selectionKey.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doWriter(SocketChannel sc, String data) throws IOException {
        final byte[] bytes = data.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        sc.write(byteBuffer);
        //****此处不含处理“写半包”的代码
    }

    public boolean sendMsg(String msg) throws IOException {
        if (msg.equalsIgnoreCase("q")) {
            return false;
        }
        socketChannel.register(selector, SelectionKey.OP_READ);
        doWriter(socketChannel, msg);
        return true;
    }
}
