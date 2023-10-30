package org.example;

public class SynchronizedBlockCounter {

    private int count = 0;

    public void incrementCount() {
        synchronized (this) {
            count = count + 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SynchronizedBlockCounter counter = new SynchronizedBlockCounter();

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
