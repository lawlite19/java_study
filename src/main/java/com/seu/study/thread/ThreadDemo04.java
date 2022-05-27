package com.seu.study.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDemo04 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> futureTask = executorService.submit(new CallableDemo());

        if (!futureTask.isDone()) {
            System.out.println("任务还未执行完毕，waiting...");
        }
        try {
            System.out.println("任务返回值是：" + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
