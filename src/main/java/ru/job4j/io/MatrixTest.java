package ru.job4j.io;

public class MatrixTest {
    public static void main(String[] args) {
        int size = 9;
        int[][] result = Matrix.multiple(size);
        Matrix.printResult(result);
    }
}
