package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        validate(argsName);
        List<List<String>> list = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new FileInputStream(argsName.get("path")));
             Scanner numScanner = new Scanner(argsName.get("filter")).useDelimiter(",")) {
            while (fileScanner.hasNextLine()) {
                List<String> line = new ArrayList<>();
                try (Scanner strScanner = new Scanner(fileScanner.nextLine())
                        .useDelimiter(argsName.get("delimiter"))) {
                    while (strScanner.hasNext()) {
                        line.add(strScanner.next());
                    }
                }
                list.add(line);
            }
            while (numScanner.hasNext()) {
                indexes.add(list.get(0).indexOf(numScanner.next()));
            }
            if (indexes.contains(-1)) {
                throw new IllegalArgumentException(
                        String.format("Incorrect some column name %s", argsName.get("filter")));
            }
            if ("stdout".equals(argsName.get("out"))) {
                for (List<String> line : list) {
                    for (Integer num : indexes) {
                        if (indexes.indexOf(num) != indexes.size() - 1) {
                            System.out.print((line.get(num) + ";"));
                        } else {
                            System.out.print((line.get(num)));
                        }
                    }
                    System.out.println();
                }
            } else {
                try (PrintWriter writer = new PrintWriter(argsName.get("out"))) {
                    for (List<String> line : list) {
                        for (Integer num : indexes) {
                            if (indexes.indexOf(num) != indexes.size() - 1) {
                                writer.write(line.get(num) + ";");
                            } else {
                                writer.write(line.get(num));
                            }
                        }
                        writer.write("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        File source = new File(argsName.get("path"));
        if (!source.exists()) {
            throw new IllegalArgumentException(String.format(
                    "Invalid path folder \"%s\"", argsName.get("path")));
        }
        if (source.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "File \"%s\" is not a directory", source.getName()));
        }
        if (!argsName.get("delimiter").contains(";")) {
            throw new IllegalArgumentException(String.format(
                    "Invalid delimiter \"%s\"", argsName.get("delimiter")));
        }
    }

    public static void main(String[] args) {
        handle(ArgsName.of(args));
    }
}
