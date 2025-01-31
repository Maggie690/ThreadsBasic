package com.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {

    private final Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500_000;

    public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        int eatenSushi = 0;
        while (sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            secondChopstick.lock();

            // take a piece of sushi
            if (sushiCount > 0) {
                sushiCount--;
                eatenSushi++;
                System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
            }

            // put down chopsticks
            secondChopstick.unlock();
            firstChopstick.unlock();
        }

        System.out.println(this.getName() + " was able to eat " + eatenSushi);
    }
}

public class AbandonedLockDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        for (int i = 0; i < 5000; i++) {
            new Philosopher("Barron_" + i, chopstickA, chopstickB).start();
            new Philosopher("Olivia_" + i, chopstickA, chopstickB).start();
            new Philosopher("Steve_" + i, chopstickA, chopstickB).start();
        }
    }
}
