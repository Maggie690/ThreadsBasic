package com.example.sleep;

public class InterruptSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread 1: Sleeping for 5 seconds...");
            try {
                Thread.sleep(5000);
                System.out.println("Thread 1: Woke up!");
            } catch (InterruptedException e) {
                System.out.println("Thread 1: Interrupted!");
            }
        });

        thread.start();

        Thread.sleep(2000);//interrupt the thread after 2 sec
        System.out.println("Main thread: Interrupting Thread.");
        thread.interrupt();
    }
}
