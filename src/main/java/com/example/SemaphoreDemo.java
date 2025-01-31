package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class SemaphoreDemo {

    public static final int NUMBER_OF_CELL_PHONES = 10;

    public static void main(String[] args) {
        for (int i = 0; i < NUMBER_OF_CELL_PHONES; i++) {
            new CellPhone("Phone-" + i).start();
        }
    }
}

class CellPhone extends Thread {
    private final Log log = LogFactory.getLog(CellPhone.class);
    private static final Semaphore charger = new Semaphore(4);//1 - BinarySemaphore

    public CellPhone(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            charger.acquire();
            System.out.println(this.getName() + " is charging...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            System.out.println(this.getName() + " is DONE charging!");
            charger.release();
        }
    }
}
