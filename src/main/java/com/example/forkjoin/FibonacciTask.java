package com.example.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Long> {

    private final long number;

    public FibonacciTask(long number) {
        this.number = number;
    }

    @Override
    protected Long compute() {
        if (number <= 1) {
            return number;
        }

        //split the task into two subtasks
        FibonacciTask task = new FibonacciTask(number - 1);
        FibonacciTask task1 = new FibonacciTask(number - 2);

        //fork the subtasks
        task.fork();
        task1.fork();

        //wait for result and combine them
        long result = task.join();
        long result1 = task1.join();

        return result + result1;
    }

    static class App {
        public static void main(String[] args) {
            ForkJoinPool pool = new ForkJoinPool();

            FibonacciTask task = new FibonacciTask(40);
            long result = pool.invoke(task);

            System.out.println("Fibonacci of 40: " + result);//102334155 in 40 steps
        }
    }
}
