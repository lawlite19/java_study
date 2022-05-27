package com.seu.study.thread;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerDemo06 {
    public static void main(String[] args) {
        Person person = new Person();

        new Thread(new Producer(person)).start();
        new Thread(new Consumer(person)).start();
    }
}


class Producer implements Runnable {
    private Person person;

    public Producer(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                this.person.set("小队长", "男性");
            } else {
                this.person.set("test", "女性");
            }
        }
    }
}

class Consumer implements Runnable {
    private Person person;

    public Consumer(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            this.person.get();
        }
    }
}

@Getter
@Setter
class Person {
    private String name;

    private String sex;

    private boolean isEmpty = true;

    // 创建可重入锁对象
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void set(String name, String sex) {
        lock.lock();
        while (!isEmpty) {
            // 不为空，需要进行等待，消费完成之后再生产
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.name = name;
        try {
            Thread.sleep(100);
            this.sex = sex;
            System.out.println("producer: " + name + "  " + sex);
            isEmpty = false;  // 设置不为空

            condition.signal(); // 唤醒消费者，可以进行消费了
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void get() {
        lock.lock();
        while (isEmpty) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("consumer: " + name + "  " + sex);

            isEmpty = true;  // 消费完成，为空

            condition.signal(); // 唤醒生产者，已经消费完，可以进行生产了
        } finally {
            lock.unlock();
        }
    }
}
