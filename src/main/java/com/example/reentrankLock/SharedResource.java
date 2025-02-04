package com.example.reentrankLock;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Only one thread executes at a time, preventing race conditions.
     */
    public void printNumbers() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static class App {
        public static void main(String[] args) {
            SharedResource resource = new SharedResource();

            Thread t0 = new Thread(resource::printNumbers);
            Thread t1 = new Thread(resource::printNumbers);

            t0.start();
            t1.start();
        }
    }
}
