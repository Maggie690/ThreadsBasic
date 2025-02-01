package com.example;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        Shopper[] shoppers = new Shopper[10];
        for (int i = 0; i < shoppers.length; i++) {
            shoppers[i] = new Shopper(i % 2 == 0 ? Shopper.MAGGIE : Shopper.OLIVIA);
        }

        for (Shopper shopper : shoppers) {
            shopper.start();
        }

        for (Shopper shopper : shoppers) {
            shopper.join();
        }

        System.out.println("We need to buy " + Shopper.bagsOfChips + " bags of chips.");
    }
}

class Shopper extends Thread {
    public static final String OLIVIA = "Olivia";
    public static final String MAGGIE = "Maggie";
    public static int bagsOfChips = 1;
    private static final String ADD = " ADDED three bags of chips.";
    private static final String DOUBLE = " DOUBLED the bags of chips.";
    private static final Lock pencil = new ReentrantLock();

    public Shopper(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            pencil.lock();
            boolean isOlivia = this.getName().contains(OLIVIA);
            calculateChips(isOlivia);

            System.out.println(this.getName() + (isOlivia ? ADD : DOUBLE));
        } finally {
            pencil.unlock();
        }
    }

    private void calculateChips(boolean isOlivia) {
        if (isOlivia) {
            bagsOfChips += 3;
        } else {
            bagsOfChips *= 3;
        }
    }
}
