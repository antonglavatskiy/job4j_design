package ru.job4j.search;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchByCriterion {
    private static void saveLog(List<Path> paths, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            if (paths.size() == 0) {
                writer.write("File not found");
            }
            for (Path path : paths) {
                writer.write(path.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> searchByCondition(Path root, Predicate<Path> condition) throws IOException {
        SearchVisitor searcher = new SearchVisitor(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void search(ArgsName argsName) {
        validate(argsName);
        switch (argsName.get("t")) {
            case "name":
                try {
                    List<Path> rsl = searchByCondition(Path.of(argsName.get("d")),
                            path -> path.toFile().getName().equals(argsName.get("n")));
                    saveLog(rsl, argsName.get("o"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "mask":
                PathMatcher pathMatcher = FileSystems.getDefault()
                        .getPathMatcher("glob:" + argsName.get("n"));
                try {
                    List<Path> rsl = searchByCondition(Path.of(argsName.get("d")),
                            path -> pathMatcher.matches(Paths.get(path.toFile().getName())));
                    saveLog(rsl, argsName.get("o"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "regex":
                try {
                    List<Path> rsl = searchByCondition(Path.of(argsName.get("d")),
                            path -> {
                        Pattern pattern = Pattern.compile(argsName.get("n"));
                        Matcher matcher = pattern.matcher(path.toFile().getName());
                        return matcher.find();
                            });
                    saveLog(rsl, argsName.get("o"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new IllegalArgumentException(String.format("Type \"%s\" is not supported."
                        + " Use only mask, name or regex", argsName.get("t")));
        }
    }

    private static void validate(ArgsName argsName) {
        File source = new File(argsName.get("d"));
        if (!source.exists()) {
            throw new IllegalArgumentException(String.format(
                    "Invalid path folder \"%s\"", argsName.get("d")));
        }
        if (!source.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "File \"%s\" is not a directory", source.getName()));
        }
    }

    public static void main(String[] args) {
        search(ArgsName.of(args));
    }
}
