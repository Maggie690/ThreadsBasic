package com.example.sleep;

public class SleepScheduling {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread 1: Sleeping for 2 seconds...");
            try {
                Thread.sleep(2000);
                System.out.println("Thread 1: Woke up!");
            } catch (InterruptedException e) {
                System.out.println("Thread 1: Interrupted!");
            }
        });

        Thread thread0 = new Thread(() -> System.out.println("Thread 2: Running immediately"));

        thread.start();
        thread0.start();
    }
}
