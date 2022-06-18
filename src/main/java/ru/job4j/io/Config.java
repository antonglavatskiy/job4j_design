package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(str -> !str.isEmpty())
                    .filter(str -> str.charAt(0) != '#')
                    .forEach(rsl::add);
            String[] arr = rsl.toString().split("\n");
            for (String str : arr) {
                int index = str.indexOf('=');
                if (index == -1
                        || index == 0
                        || (index == str.lastIndexOf('=') && index == str.length() - 1)) {
                    throw new IllegalArgumentException();
                }
                values.put(str.substring(0, index), str.substring(index + 1));
            }
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
