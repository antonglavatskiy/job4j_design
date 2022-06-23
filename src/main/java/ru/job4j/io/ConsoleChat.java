package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswer;

    public ConsoleChat(String path, String botAnswer) {
        this.path = path;
        this.botAnswer = botAnswer;
    }

    public void run() {
        boolean botWork = true;
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        String userAnswer = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!STOP.equals(userAnswer)) {
                userAnswer = reader.readLine();
                log.add(userAnswer);
                if (OUT.equals(userAnswer) && botWork) {
                    botWork = false;
                }
                if (CONTINUE.equals(userAnswer) && !botWork) {
                    botWork = true;
                }
                if (botWork && !STOP.equals(userAnswer)) {
                    Random random = new Random();
                    int index = random.nextInt(phrases.size());
                    System.out.println(phrases.get(index));
                    log.add(phrases.get(index));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswer, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.stream().forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./consoleBot/chatLog.txt", "./consoleBot/botAnswer.txt");
        cc.run();
    }
}
