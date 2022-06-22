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

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsZip = ArgsName.of(args);
        Search searchZip = Search.of(new String[] {argsZip.get("d"), argsZip.get("e")});
        List<Path> source = searchZip.search(Paths.get(argsZip.get("d")),
                path -> !path.toFile().getName().endsWith(argsZip.get("e")));
        zip.packFiles(source, new File(argsZip.get("o")));
    }
}
