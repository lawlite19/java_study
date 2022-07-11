package com.seu.study.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class AioServer {
    static int PORT = 8080;
    static String CHARSET = "utf-8"; //默认编码
    static CharsetDecoder decoder = Charset.forName(CHARSET).newDecoder(); //解码

    int port;
    AsynchronousServerSocketChannel serverChannel;

    public AioServer(int port) throws IOException {
        this.port = port;
    }

    public void listen() throws Exception {
        //打开一个服务通道
        //绑定服务端口
        this.serverChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port), 100);
        // 接收连接请求
        this.serverChannel.accept(this, new AcceptHandler());

        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("运行中...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public static void close(AsynchronousSocketChannel client) {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("正在启动服务...");
            AioServer server = new AioServer(PORT);
            server.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
