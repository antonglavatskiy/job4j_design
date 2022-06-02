package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data[row].length == 0) {
            row++;
        }
        return data.length > row && data[row].length > column;
    }

    @Override
    public Integer next() {
        while (data[row].length == 0) {
            row++;
            if (row == data.length) {
                row--;
                break;
            }
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer rsl = data[row][column++];
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        return rsl;
    }

    public static void main(String[] args) {
        int[][] in = {
                {}
        };
        System.out.println(in.length);
        System.out.println(in[0].length);
    }
}
