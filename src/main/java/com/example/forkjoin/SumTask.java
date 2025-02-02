package com.example.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {
    private final int[] array;
    private final int start, end;
    private static final int THRESHOLD = 2;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            //   System.out.println("-----> sum " + sum);
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            leftTask.fork();//asynchronous execution

            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}

class ForkJoinExaple {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        ForkJoinPool pool = new ForkJoinPool();

        SumTask task = new SumTask(array, 0, array.length);
        int sum = pool.invoke(task);

        System.out.println("Sum: " + sum);
    }
}
