package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            result = reader
                    .lines()
                    .filter(str -> str.contains(" 404 "))
                    .collect(Collectors.toList());
            /*
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] arr = line.split(" ");
                if ("404".equals(arr[arr.length - 2])) {
                    result.add(line);
                }
            }
             */

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
