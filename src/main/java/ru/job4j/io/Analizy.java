package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
             BufferedReader reader = new BufferedReader(new FileReader(source))) {
            boolean serverIsOn = true;
            String line;
            while ((line = reader.readLine()) != null) {
                if ((line.contains("400") || line.contains("500")) && serverIsOn) {
                    out.write(line.substring(4) + ";");
                    serverIsOn = false;
                }
                if (line.contains("200") && !serverIsOn) {
                    out.write(line.substring(4) + ";");
                    out.write(System.lineSeparator());
                    serverIsOn = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "./server/server.log";
        String target = "./server/unavailable.csv";
        Analizy.unavailable(source, target);
    }
}
