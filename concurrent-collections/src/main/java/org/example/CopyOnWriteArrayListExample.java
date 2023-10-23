package org.example;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {

    private CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

    public void addElement(int element) {
        list.add(element);
    }

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayListExample copyOnWriteArrayListExample = new CopyOnWriteArrayListExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                copyOnWriteArrayListExample.addElement(i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                copyOnWriteArrayListExample.addElement(i);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(copyOnWriteArrayListExample.list.size());
    }
}