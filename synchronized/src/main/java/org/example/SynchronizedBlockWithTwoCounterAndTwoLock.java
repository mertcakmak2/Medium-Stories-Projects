package org.example;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBlockWithTwoCounterAndTwoLock {

    private int firstCount = 0;
    private ReentrantLock firstLock = new ReentrantLock();
    private int secondCount = 0;
    private ReentrantLock secondLock = new ReentrantLock();

    public void incrementFirstCount() throws InterruptedException {
        synchronized (firstLock) {
            Thread.sleep(1000);
            firstCount = firstCount + 1;
        }
    }

    public void incrementSecondCount() throws InterruptedException {
        synchronized (secondLock) {
            Thread.sleep(1000);
            secondCount = secondCount + 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SynchronizedBlockWithTwoCounterAndTwoLock counter = new SynchronizedBlockWithTwoCounterAndTwoLock();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    counter.incrementFirstCount();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    counter.incrementSecondCount();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        System.out.printf("Start Date: %s%n", new Date());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.printf("First Count: %s%n", counter.firstCount);
        System.out.printf("Second Count: %s%n", counter.secondCount);

        System.out.printf("Finish Date: %s%n", new Date());

    }
}
