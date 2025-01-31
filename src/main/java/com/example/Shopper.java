package com.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shopper extends Thread {
    static int garlicCount = 0;
    static Lock pencil = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            pencil.lock();
            garlicCount++;
            pencil.unlock();

            System.out.println(Thread.currentThread().getName() + " is thinking");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }

            System.out.println();
        }
    }

    public static class MutualExclusionDemon {
        public static void main(String[] args) throws InterruptedException {
            Thread olivia = new Shopper();
            Thread maggie = new Shopper();

            olivia.setName("Olivia");
            maggie.setName("Maggie");

            olivia.start();
            maggie.start();

            olivia.join();
            maggie.join();

            System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
        }
    }
}
