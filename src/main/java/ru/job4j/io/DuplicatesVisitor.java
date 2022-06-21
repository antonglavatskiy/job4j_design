package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private  Map<FileProperty, List<Path>> map = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        map.putIfAbsent(fileProperty, new ArrayList<>());
        map.computeIfPresent(fileProperty, (key, value) -> {
            value.add(file.toAbsolutePath());
            return value;
        });
        return super.visitFile(file, attrs);
    }

    public void print() {
        for (FileProperty fileProperty : map.keySet()) {
            if (map.get(fileProperty).size() > 1) {
                System.out.println(String.format("File \"%s %d bytes\" repeated %d times",
                        fileProperty.getName(), fileProperty.getSize(), map.get(fileProperty).size()));
                map.get(fileProperty).forEach(System.out::println);
            }
        }
    }
}
