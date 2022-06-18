package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class Abuse {
    public static void drop(String source, String target, List<String> words) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            reader.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(str -> !words.contains(str))
                    .map(word -> word + " ")
                    .forEach(out::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
