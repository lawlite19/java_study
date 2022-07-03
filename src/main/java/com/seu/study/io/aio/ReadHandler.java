package com.seu.study.io.aio;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
public class ReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
    private ByteBuffer buffer;

    public ReadHandler(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void completed(Integer result, AsynchronousSocketChannel attachment) {
        try {
            if (result < 0) {// 客户端关闭了连接
                AioServer.close(attachment);
            } else if (result == 0) {
                System.out.println("空数据"); // 处理空数据
            } else {
                // 读取请求，处理客户端发送的数据
                buffer.flip();
                CharBuffer charBuffer = AioServer.decoder.decode(buffer);
                System.out.println(charBuffer.toString()); //接收请求

                //响应操作，服务器响应结果
                buffer.clear();
                String res = "HTTP/1.1 200 OK" + "\r\n\r\n" + "hellworld";
                buffer = ByteBuffer.wrap(res.getBytes());
                attachment.write(buffer, attachment, new WriteHandler(buffer));//Response：响应。
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        exc.printStackTrace();
        AioServer.close(attachment);
    }
}
