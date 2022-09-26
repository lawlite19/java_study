package com.seu.study.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerV3 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
        while (true) {
            System.out.println("等待连接...");
            // 程序阻塞，等待客户端连接
            final Socket client = serverSocket.accept();
            System.out.println("有客户端连接服务端...");
            // 使用线程池处理请求
            executorService.execute(() -> handle(client));
        }
    }

    private static void handle(Socket client) {
        try (InputStream inputStream = client.getInputStream();) {
            byte[] bytes = new byte[1024];
            // 程序阻塞，没有数据可读时就阻塞
            while ((inputStream.read(bytes)) != -1) {
                System.out.println("读取客户端数据：" +
                        new String(bytes, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
