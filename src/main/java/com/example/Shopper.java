package com.example;

public class Shopper extends Thread {
    static int garlicCount = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10_000_000; i++) {
            garlicCount++;
        }
    }

    public static class MutualExclusionDemon {
        public static void main(String[] args) throws InterruptedException {
            Thread olivia = new Shopper();
            Thread maggie = new Shopper();

            olivia.start();
            maggie.start();

            olivia.join();
            maggie.join();

            System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
        }
    }
}
