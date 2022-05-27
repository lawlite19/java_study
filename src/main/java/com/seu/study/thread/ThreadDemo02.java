package com.seu.study.thread;

public class ThreadDemo02 implements Runnable{
    public static String value;

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo02 threadDemo02 = new ThreadDemo02();
        Thread thread = new Thread(threadDemo02);
        thread.start();

        thread.join();
        System.out.println(value);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = "set value";
    }
}
