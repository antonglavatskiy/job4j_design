package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            StringBuilder numbers = new StringBuilder();
            while ((read = in.read()) != -1) {
                numbers.append((char) read);
            }
            for (String str : numbers.toString().split(System.lineSeparator())) {
                int num = Integer.parseInt(str);
                String result = Integer.parseInt(str) % 2 == 0 ? num + " is even" : num + " is odd";
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
