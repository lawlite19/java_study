package com.seu.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallableDemo());
        new Thread(futureTask).start();

        if (!futureTask.isDone()) {
            System.out.println("任务还未执行完毕，waiting...");
        }
        System.out.println("任务返回值是：" + futureTask.get());
    }

}

class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        String value = "value";
        System.out.println("执行复杂业务逻辑...");
        Thread.sleep(5000);
        System.out.println("复杂业务逻辑执行完毕...");
        return value;
    }
}
