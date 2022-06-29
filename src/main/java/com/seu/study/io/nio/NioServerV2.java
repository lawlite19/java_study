package com.seu.study.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 使用selector
 */
public class NioServerV2 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        // 设置ServerSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 打开Selector处理Channel，也就是使用epoll
        Selector selector = Selector.open();
        // 把ServerSocketChannel注册到Selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动成功");

        while (true) {
            // 阻塞等待需要处理的事件发生
            selector.select();

            // 获取selector中注册的全部时间的SelectKey实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    // 如果是OP_ACCEPT事件，则进行连接和事件注册
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    // 客户端SocketChannel同样注册到Selector中，服务端监听读事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                } else if (selectionKey.isReadable()) {
                    // 如果是OP_READ事件，则进行数据读取和打印
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                    int len = socketChannel.read(byteBuffer);
                    if (len > 0) {
                        System.out.println("读取客户端数据: " + new String(byteBuffer.array(), StandardCharsets.UTF_8));
                    } else if (len == -1) {
                        // 客户端断开连接，从连接中移除。
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }
                    // 从事件集合中删除本次处理的key, 防止限次select重复处理
                    iterator.remove();
                }
            }
        }
    }
}
