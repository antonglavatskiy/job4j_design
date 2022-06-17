package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {
    public static int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                matrix[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return matrix;
    }

    public static void printResult(int[][] matrix) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int row = 0; row < matrix.length; row++) {
                for (int cell = 0; cell < matrix[row].length; cell++) {
                    out.write((matrix[row][cell] + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
