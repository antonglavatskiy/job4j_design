package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getAbsolutePath()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(in.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(String[] args) {
        ArgsName argsZip = ArgsName.of(args);
        args[0] = argsZip.get("d");
        args[1] = argsZip.get("e");
        args[2] = argsZip.get("o");
        File search = new File(args[0]);
        if (!search.exists()) {
            throw new IllegalArgumentException(String.format(
                    "Invalid path folder \"%s\"", args[0]));
        }
        if (!search.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "File \"%s\" is not a directory", search.getName()));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(String.format(
                    "Invalid file extension \"%s\"", args[1]));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validate(args);
        List<Path> source = Search.search(Paths.get(args[0]),
                path -> !path.toFile().getName().endsWith(args[1]));
        zip.packFiles(source, new File(args[2]));
    }
}
