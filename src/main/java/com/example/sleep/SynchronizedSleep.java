package com.example.sleep;

/**
 * If the thread is inside a synchronized block and calls Thread.sleep(),
 * the lock will still be held during the sleep. Other threads that try
 * to access the same synchronized block will be blocked
 * until the sleeping thread finishes sleeping and releases the lock.
 * <p>
 * Thread 2 tries to acquire the lock but is blocked until Thread 1
 * finishes sleeping and releases the lock.
 */
public class SynchronizedSleep {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1: Holding lock and sleeping for 3 seconds...");
                try {
                    Thread.sleep(3000);
                    System.out.println("Thread 1: Woke up and releasing lock!");
                } catch (InterruptedException e) {
                    System.out.println("Thread 1: Interrupted!");
                }
            }
        });

        Thread thread0 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2: Acquired lock!");

            }
        });

        thread.start();
        thread0.start();
    }
}
