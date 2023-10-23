package org.example;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public void putElement(String key, int value) {
        map.put(key, value);
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMapExample concurrentHashMapExample = new ConcurrentHashMapExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                String key = String.format("%s%s","i",i);
                concurrentHashMapExample.putElement(key, i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                String key = String.format("%s%s","j",j);
                concurrentHashMapExample.putElement(key, j);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(concurrentHashMapExample.map.size());
    }
}
