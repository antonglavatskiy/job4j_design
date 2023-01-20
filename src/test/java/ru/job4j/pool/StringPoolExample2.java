package ru.job4j.pool;

public class StringPoolExample2 {
    public static void main(String[] args) {
        String string1 = "Hello, world";
        String string2 = "Hello, " + "world";
        System.out.println(string1 == string2);
    }
}
