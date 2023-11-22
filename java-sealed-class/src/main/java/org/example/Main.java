package org.example;

public class Main {
    public static void main(String[] args) {

        Vehicle v = new BusinessCar();
        v.setId(1);

        System.out.println(v.toString());
    }
}

