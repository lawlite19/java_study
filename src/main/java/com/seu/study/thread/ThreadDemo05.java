package com.seu.study.thread;

public class ThreadDemo05 {
    public static void main(String[] args) {
        Example example = new Example();

        new Thread(new TheThread(example)).start();
        new Thread(new TheThread(example)).start();
    }
}


class Example {
    public synchronized void execute() {
        for (int i=0;i<10;i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }

    public void execute2() {
        for (int i=0;i<10;i++) {
            synchronized(this) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class TheThread implements Runnable {
    private Example example;

    public TheThread(Example example) {
        this.example = example;
    }

    @Override
    public void run() {
        example.execute();
    }
}
