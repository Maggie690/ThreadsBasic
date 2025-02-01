package com.example;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceConditionBarrier {

    public static final int NUMBER_OF_SHOPPER = 10;

    public static void main(String[] args) throws InterruptedException {
        ShopperUS[] shopperUSES = new ShopperUS[NUMBER_OF_SHOPPER];
        for (int i = 0; i < shopperUSES.length; i++) {
            shopperUSES[i] = new ShopperUS(i % 2 == 0 ? ShopperUS.MAGGIE : ShopperUS.OLIVIA);
        }

        for (ShopperUS shopperUS : shopperUSES) {
            shopperUS.start();
        }

        for (ShopperUS shopperUS : shopperUSES) {
            shopperUS.join();
        }

        System.out.println("We need to buy " + ShopperUS.bagsOfChips + " bags of chips.");
    }
}

class ShopperUS extends Thread {
    public static final String OLIVIA = "Olivia";
    public static final String MAGGIE = "Maggie";
    public static int bagsOfChips = 1;
    private static final String ADD = " ADDED three bags of chips.";
    private static final String DOUBLE = " DOUBLED the bags of chips.";
    private static final Lock pencil = new ReentrantLock();
    private static final CyclicBarrier barrier = new CyclicBarrier(RaceConditionBarrier.NUMBER_OF_SHOPPER);

    public ShopperUS(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        boolean isOlivia = this.getName().contains(OLIVIA);

        try {
            if (isOlivia) {
                lockPerson(isOlivia);
                barrier.await();

            } else {
                barrier.await();
                lockPerson(isOlivia);
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            ex.printStackTrace();
        }
    }

    private void lockPerson(boolean isOlivia) {
        try {
            pencil.lock();

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
            bagsOfChips *= 2;
        }
    }
}
