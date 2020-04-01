package com.exercise.socket.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadEchoServer {

	private int port;

	public SingleThreadEchoServer(int port) {
		this.port = port;
	}

	public void startServer() {
		ServerSocket echoServer = null;
		int i = 0;
		System.out.println("服务器在端口[" + this.port + "]等待客户请求......");
		try {
			echoServer = new ServerSocket(this.port);
			while (true) {
				Socket clientRequest = echoServer.accept();
				handleRequest(clientRequest, i++);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private void handleRequest(Socket clientSocket, int clientNo) {
		PrintStream os = null;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
			String inputLine;
			while ((inputLine = in.readLine()) != null) {

				// 输入'Quit'退出
				if (inputLine.equals("Quit")) {
					System.out.println("关闭与客户端[" + clientNo + "]......" + clientNo);
					os.close();
					in.close();
					clientSocket.close();
					break;
				} else {
					System.out.println("来自客户端[" + clientNo + "]的输入： [" + inputLine + "]！");
					os.println("来自服务器端的响应：" + inputLine);
				}
			}
		} catch (IOException e) {
			System.out.println("Stream closed");
		}
	}

	public static void main(String[] args) throws IOException {
		new SingleThreadEchoServer(8080).startServer();
	}
}
