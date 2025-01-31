package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<String> servingLine = new ArrayBlockingQueue<>(5); //5 Queue full
        new SoupConsumer(servingLine).start();
        //   new SoupConsumer(servingLine).start();
        new SoupProducer(servingLine).start();
    }
}

class SoupProducer extends Thread {
    private final Log log = LogFactory.getLog(SoupProducer.class);

    private static final int NUMBER_OF_BOWLS = 20;
    private BlockingQueue<String> servingLine;

    public SoupProducer(BlockingQueue<String> servingLine) {
        this.servingLine = servingLine;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUMBER_OF_BOWLS; i++) {
            try {
                servingLine.add("Bowl №" + i);
                System.out.format("Served Bowl №%d - remaining capacity: %d\n", i, servingLine.remainingCapacity());
                Thread.sleep(200);
            } catch (Exception ex) {
                log.error(ex.getMessage());
                //  ex.printStackTrace();
            }
        }
    }
}

class SoupConsumer extends Thread {
    private final Log log = LogFactory.getLog(SoupConsumer.class);

    private BlockingQueue<String> servingLine;

    public SoupConsumer(BlockingQueue<String> servingLine) {
        this.servingLine = servingLine;
    }

    @Override
    public void run() {
        try {
            String bowl = servingLine.take();
            System.out.format("Ate %s\n", bowl);
            Thread.sleep(300);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
