package org.example;

import java.util.Date;

public class SynchronizedMethodWithTwoCounter {

    private int firstCount = 0;
    private int secondCount = 0;

    public synchronized void incrementFirstCount() throws InterruptedException {
        Thread.sleep(1000);
        firstCount = firstCount + 1;
    }

    public synchronized void incrementSecondCount() throws InterruptedException {
        Thread.sleep(1000);
        secondCount = secondCount + 1;
    }

    public static void main(String[] args) throws InterruptedException {

        SynchronizedMethodWithTwoCounter counter = new SynchronizedMethodWithTwoCounter();

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
