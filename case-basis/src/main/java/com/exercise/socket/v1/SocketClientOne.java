package com.exercise.socket.v1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class SocketClientOne {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
//            outputStream(socket.getOutputStream());
            bufferedWriter(socket.getOutputStream());
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void outputStream(OutputStream os) throws IOException {
        os.write("hello,world one".getBytes());
    }

    public static void bufferedWriter(OutputStream outputStream) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        while (true) {
            final String line = bufferedReader.readLine();
            if (line.equalsIgnoreCase("ok")) {
                break;
            }
            writer.write(line);
            writer.write("\n");
            // 如果不flush 服务器端接受的值为空
            writer.flush();
        }
    }
}
