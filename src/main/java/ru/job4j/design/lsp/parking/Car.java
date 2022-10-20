package ru.job4j.design.lsp.parking;

public class Car implements Vehicle {
    public static final int SIZE = 1;

    @Override
    public int getSize() {
        return SIZE;
    }
}
