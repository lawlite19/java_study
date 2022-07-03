package com.seu.study.io.aio;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
    static int BUFFER_SIZE = 1024;
    @Override
    public void completed(final AsynchronousSocketChannel client, AioServer attachment) {
        try {
            System.out.println("远程地址：" + client.getRemoteAddress());
            //tcp各项参数
            client.setOption(StandardSocketOptions.TCP_NODELAY, true);
            client.setOption(StandardSocketOptions.SO_SNDBUF, 1024);
            client.setOption(StandardSocketOptions.SO_RCVBUF, 1024);

            if (client.isOpen()) {
                System.out.println("client.isOpen：" + client.getRemoteAddress());
                final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                buffer.clear();
                client.read(buffer, client, new ReadHandler(buffer));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            attachment.serverChannel.accept(attachment, this);// 监听新的请求，递归调用。
        }
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        try {
            exc.printStackTrace();
        } finally {
            attachment.serverChannel.accept(attachment, this);// 监听新的请求，递归调用。
        }
    }
}
