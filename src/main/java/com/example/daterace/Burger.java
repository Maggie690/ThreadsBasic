package com.example.daterace;

import java.util.List;

public class Burger extends Thread {
    private List<String> ingredients = List.of("kosher salt", "granulated garlic", "onion", "dry mustard", "black pepper", "honey", "meal");
    static int ingredientCount = 0;

    @Override
    public void run() {
        for (String ingredient : ingredients) {
            synchronized (Burger.class) {
                ingredientCount++;
            }

            System.out.println(Thread.currentThread().getName() + " added " + ingredient);
        }
    }

    public static class MutualExclusionDemon {
        public static void main(String[] args) throws InterruptedException {
            Thread olivia = new Burger();
            Thread maggie = new Burger();

            olivia.setName("Chief Master Olivia");
            maggie.setName("Chief Master Maggie");

            olivia.start();
            maggie.start();

            olivia.join();
            maggie.join();
        }
    }
}
