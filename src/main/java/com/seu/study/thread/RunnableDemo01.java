package com.seu.study.thread;

public class RunnableDemo01 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable1());
        thread.start();
    }
}

class Runnable1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
