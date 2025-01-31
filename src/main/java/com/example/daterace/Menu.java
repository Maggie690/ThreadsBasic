package com.example.daterace;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu extends Thread {
    private List ingredients = List.of("kosher salt", "granulated garlic", "onion", "dry mustard", "black pepper", "honey", "meal");
    static int ingredient = 0;

    private static synchronized void addIngredients() {
        ingredient++;
    }

    @Override
    public void run() {
        for (int i = 0; i < ingredients.size(); i++) {
            addIngredients();

            System.out.println(Thread.currentThread().getName() + " added " + ingredients.get(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
        }
    }

    public static class MutualExclusionDemon {
        public static void main(String[] args) throws InterruptedException {
            Thread olivia = new Menu();
            Thread maggie = new Menu();

            olivia.setName("Olivia");
            maggie.setName("Maggie");

            olivia.start();
            maggie.start();

            olivia.join();
            maggie.join();
        }
    }
}
