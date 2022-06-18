package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            values = reader.lines()
                    .filter(str -> !str.isEmpty())
                    .filter(str -> str.charAt(0) != '#')
                    .flatMap(str -> Stream.of(str.split(System.lineSeparator())))
                    .filter(str -> {
                        int index = str.indexOf('=');
                        if (index == -1
                                || index == 0
                                || (index == str.lastIndexOf('=')
                                && index == str.length() - 1)) {
                            throw new IllegalArgumentException();
                        }
                        return true;
                    })
                    .collect(Collectors.toMap(str -> str.substring(0, str.indexOf('=')),
                            str -> str.substring(str.indexOf('=') + 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner str = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .forEach(str::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
