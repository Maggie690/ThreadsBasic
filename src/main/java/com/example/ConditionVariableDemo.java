package com.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionVariableDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new HungryPerson(i).start();
        }
    }
}

class HungryPerson extends Thread {
    public static final int NUMBER_OF_HUNGRY_PERSON = 5;
    private static int servings = 11;
    private static Lock slowCookerLid = new ReentrantLock();
    private static Condition soupTaken = slowCookerLid.newCondition();
    private final int personID;

    public HungryPerson(int personID) {
        this.personID = personID;
    }

    @Override
    public void run() {
        while (servings > 0) {
            slowCookerLid.lock();

            try {
                while ((personID != servings % NUMBER_OF_HUNGRY_PERSON) && servings > 0) { // check is the turn to eat soup
                    System.out.format("Person %d checked... then put the lid back.\n", personID);
                    soupTaken.await();
                }

                if (servings > 0) {
                    servings--;
                    System.out.format("Person %d took some soup! Servings left: %d\n", personID, servings);
                    soupTaken.signalAll(); //signal() if NUMBER_OF_HUNGRY_PERSON=2 /notify one thread/
                }

            } catch (Exception ex) {
                ex.getStackTrace();
            } finally {
                slowCookerLid.unlock();
                System.out.println("---> unlock");
            }
        }
    }
}
