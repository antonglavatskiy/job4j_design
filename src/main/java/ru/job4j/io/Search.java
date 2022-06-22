package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Search search = Search.of(args);
        Path start = Paths.get(args[0]);
        search.search(start, p -> p.toFile().getName().endsWith(args[1]))
                    .forEach(System.out::println);

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static Search of(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(String.format(
                    "Invalid number of arguments - \"%d\"", args.length));
        }
        Search search = new Search();
        search.validate(args);
        return search;
    }

    private void validate(String[] args) {
        File current = new File(args[0]);
        if (!current.exists()) {
            throw new IllegalArgumentException(String.format(
                    "Invalid path folder \"%s\"", args[0]));
        }
        if (!current.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "File \"%s\" is not a directory", current.getName()));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(String.format(
                    "Invalid file extension \"%s\"", args[1]));
        }
    }
}
