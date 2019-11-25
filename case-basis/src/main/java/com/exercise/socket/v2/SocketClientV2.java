package com.exercise.socket.v2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class SocketClientV2 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            final OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            Scanner scanner = new Scanner(System.in,"UTF-8");
            if (scanner.hasNext()) {
                final String next = scanner.next();
                dataOutputStream.writeByte(1);
                dataOutputStream.writeInt(next.getBytes().length + 5);
                dataOutputStream.write(next.getBytes("UTF-8"));
//                dataOutputStream.writeUTF(next);
                dataOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
