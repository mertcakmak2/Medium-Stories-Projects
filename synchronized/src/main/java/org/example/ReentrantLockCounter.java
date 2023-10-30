package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter {

    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void incrementCount() {
        lock.lock();
        try {
            count = count + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReentrantLockCounter counter = new ReentrantLockCounter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementCount();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementCount();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.printf("Count: %s%n", counter.count);

    }
}
