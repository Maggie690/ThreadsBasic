package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        int numberProcessor = Runtime.getRuntime().availableProcessors();

        ExecutorService pool = Executors.newFixedThreadPool(numberProcessor);
        for (int i = 0; i < 100; i++) {
            pool.submit(new VegetableChopper());
        }

        pool.shutdown();
    }
}

class VegetableChopper extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " chopped a vegetable!");
    }
}
