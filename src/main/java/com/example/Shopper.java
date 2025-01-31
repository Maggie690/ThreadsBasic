package com.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Shopper extends Thread {
    static AtomicInteger garlicCount = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            garlicCount.incrementAndGet();

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
