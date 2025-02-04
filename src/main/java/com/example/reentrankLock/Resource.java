package com.example.reentrankLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Deadlock is prevented because threads do not block indefinitely.
 * Threads try to acquire locks in opposite order,
 * but will release and retry instead of deadlocking.
 */
public class Resource {
    private final ReentrantLock lock0 = new ReentrantLock();
    private final ReentrantLock lock1 = new ReentrantLock();

    public void methodA() {
        try {
            if (lock0.tryLock(3, TimeUnit.SECONDS)) {  // prevents deadlocks by giving up after timeout.
                System.out.println(Thread.currentThread().getName() + " acquired Lock1");
                Thread.sleep(2000);

                if (lock1.tryLock(3, TimeUnit.SECONDS)) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " acquired Lock2");
                    } finally {
                        lock1.unlock();
                    }
                }
                lock0.unlock();
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire Lock1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void methodB() {
        try {
            if (lock1.tryLock(3, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " acquired Lock2");
                Thread.sleep(2000);

                if (lock0.tryLock(3, TimeUnit.SECONDS)) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " acquired Lock1");
                    } finally {
                        lock0.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not acquire Lock1");
                }

                lock1.unlock();
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire Lock2");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class App {
        public static void main(String[] args) {
            Resource resource = new Resource();

            Thread tA = new Thread(new Thread(resource::methodA), "Thread A");
            Thread tB = new Thread(new Thread(resource::methodB), "Thread B");

            tA.start();
            tB.start();
        }
    }
}
