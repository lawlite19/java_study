package com.seu.study.thread;

import java.util.concurrent.Executors;

/**
 * 线程池: 应用启动的时候准备了一些线程，需要使用的时候直接拿来使用，并不需要去创建
 *
 * server一个服务：一个请求进来，server需要开启一个线程处理
 *
 */
public class ThreadPoolDemo01 {
    public static void main(String[] args) {
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(10);
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(10);
    }
}