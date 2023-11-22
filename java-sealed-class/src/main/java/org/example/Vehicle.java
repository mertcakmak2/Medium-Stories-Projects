package org.example;

public sealed class Vehicle permits Car {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                '}';
    }
}
