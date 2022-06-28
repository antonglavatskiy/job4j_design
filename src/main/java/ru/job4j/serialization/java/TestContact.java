package ru.job4j.serialization.java;

import java.io.*;

public class TestContact {
    public static void main(String[] args) {
        Contact current = new Contact(657123, "35-37-90");
        File file = new File("contact.bin");
        try (FileOutputStream out = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(current);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Current contact: " + current);
        try (FileInputStream in = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(in)) {
            Contact returned = (Contact) ois.readObject();
            System.out.println("Returned contact: " + returned);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
