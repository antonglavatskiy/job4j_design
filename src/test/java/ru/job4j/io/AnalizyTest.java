package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailableFirst() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.csv");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("200 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner result = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines()
                    .forEach(result::add);
        }
        assertThat(result.toString(), is("10:57:01;10:59:01;\n11:01:02;11:02:02;"));
    }

    @Test
    public void unavailableSecond() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.csv");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("500 10:59:01");
            writer.println("400 11:01:02");
            writer.println("200 11:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner result = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines()
                    .forEach(result::add);
        }
        assertThat(result.toString(), is("10:57:01;11:02:02;"));
    }
}