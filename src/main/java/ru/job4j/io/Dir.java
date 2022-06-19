package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/Users/anton/projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    file.getAbsoluteFile()));
        }
        for (File someFile : file.listFiles()) {
            if (someFile.isFile()) {
                System.out.println(String.format("File \"%s\" has %d bytes",
                        someFile.getName(), someFile.length()));
            }
        }
    }
}
