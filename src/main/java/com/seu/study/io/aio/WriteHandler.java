package com.seu.study.io.aio;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
public class WriteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel>{
    private ByteBuffer buffer;

    public WriteHandler(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void completed(Integer result, AsynchronousSocketChannel attachment) {
        buffer.clear();
        AioServer.close(attachment);
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        exc.printStackTrace();
        AioServer.close(attachment);
    }
}
