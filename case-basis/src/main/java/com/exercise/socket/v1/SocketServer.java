package com.exercise.socket.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class SocketServer {


    static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        try {
            final ServerSocket serverSocket = new ServerSocket(8080);

            while (true) {
                Runnable runnable = () -> {
                    try {
                        final Socket socket = serverSocket.accept();
                        whileReadLine(socket.getInputStream());
                    } catch (IOException e) {
                    }
                };
                service.submit(runnable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readLine(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());
    }

    public static void whileReadLine(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String data = "";
        while ((data = bufferedReader.readLine()) != null) {
            System.out.println(data);
        }
    }
}
