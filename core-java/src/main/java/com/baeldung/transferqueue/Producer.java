package com.nklkarthi.transferqueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class Producer implements Runnable {
    private final TransferQueue<String> transferQueue;
    private final String name;
    private final Integer numberOfMessagesToProduce;

    public Producer(TransferQueue<String> transferQueue, String name, Integer numberOfMessagesToProduce) {
        this.transferQueue = transferQueue;
        this.name = name;
        this.numberOfMessagesToProduce = numberOfMessagesToProduce;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfMessagesToProduce; i++) {
            try {
                System.out.println("Producer: " + name + " is waiting to transfer...");
                boolean added = transferQueue.tryTransfer("A" + i, 4000, TimeUnit.MILLISECONDS);
                if (!added) {
                    System.out.println("can not add an element due to the timeout");
                } else {
                    System.out.println("Producer: " + name + " transferred element: A" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}