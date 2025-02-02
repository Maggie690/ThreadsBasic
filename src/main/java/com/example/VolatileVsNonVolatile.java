package com.example;

public class VolatileVsNonVolatile {
    private static volatile boolean flag = Boolean.FALSE;
    private static volatile boolean nonVolatileFlag = Boolean.FALSE;

    public static void main(String[] args) throws InterruptedException {
        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(1000);
                flag = true;
                nonVolatileFlag = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread reader = new Thread(() -> {
            while (!flag) { // Check volatile flag
                // Keep checking the volatile flag
            }
            System.out.println("Volatile flag is true!");

            while (!nonVolatileFlag) { // Check non-volatile flag
                // Keep checking the non-volatile flag (may not be visible)
            }
            System.out.println("Non-volatile flag is true!");
        });

        writer.start();
        reader.start();

        writer.join();
        reader.join();
    }
}
