package com.example.reentrankLock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrant Lock with Conditions
 *
 * Producer-Consumer synchronization with conditions
 */
public class SharedBuffer {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();
    private LinkedList<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;


    public void producer(int value) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == CAPACITY) {
                System.out.println("Buffer full! " + Thread.currentThread().getName() + " waiting...");
                bufferNotFull.await();
            }

            buffer.add(value);
            System.out.println(Thread.currentThread().getName() + " produced: " + value);
            bufferNotEmpty.signal(); // notify consumer
        } finally {
            lock.unlock();
        }
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                bufferNotEmpty.await();
            }

            int value = buffer.removeFirst();
            System.out.println(Thread.currentThread().getName() + " consumed: " + value);
            bufferNotFull.signal();
        } finally {
            lock.unlock();
        }
    }

    private static class App {
        public static void main(String[] args) throws InterruptedException {
            SharedBuffer resource = new SharedBuffer();

            Thread producer = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        resource.producer(i);
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Producer");

            Thread consumer = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        resource.consumer();
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Consumer");

            producer.start();
            consumer.start();
        }
    }
}
