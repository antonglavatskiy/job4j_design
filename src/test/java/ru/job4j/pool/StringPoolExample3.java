package ru.job4j.pool;

public class StringPoolExample3 {
    public static void main(String[] args) {
        String string1 = "Hello, world";
        String string2 = "Hello, ";
        String string3 = string2 + "world";
        System.out.println(string1 == string3);
    }
}
