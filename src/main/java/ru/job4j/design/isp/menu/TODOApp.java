package ru.job4j.design.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final String ENTER = System.lineSeparator();
    public static final String EMPTY = "Список задач не заполнен";
    public static final String START = """
            1. Создать список задач
            2. Напечатать список задач
            3. Выход
            """;
    public static final String CREATE = """
            * Создать список задач *
            1. Добавить основную задачу
            2. Добавить подзадачу
            3. Закончить создание списка задач
            """;
    public static final String TASK = """
            * Создать задачу *
            Введите имя задачи
            """;
    public static final String SUBTASK = """
            * Создать подзадачу *
            Введите имя задачи
            Введите имя подзадачи
            """;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        System.out.println(START);
        while (run) {
            int num = scanner.nextInt();
            scanner.nextLine();
            if (num == ONE) {
                System.out.println(CREATE);
                int choice = scanner.nextInt();
                if (choice == ONE) {
                    System.out.println(TASK);
                    scanner.nextLine();
                    String childName = scanner.nextLine();
                    menu.add(Menu.ROOT, childName, STUB_ACTION);
                    System.out.println(ENTER + START);
                } else if (choice == TWO) {
                    System.out.println(SUBTASK);
                    scanner.nextLine();
                    String parentName = scanner.nextLine();
                    String childName = scanner.nextLine();
                    menu.add(parentName, childName, STUB_ACTION);
                    System.out.println(START);
                } else if (choice == THREE) {
                    System.out.println(START);
                }
            } else if (num == TWO) {
                if (menu.iterator().hasNext()) {
                    printer.print(menu);
                } else {
                    System.out.println(EMPTY);
                }
                System.out.println(ENTER + START);
            } else if (num == THREE) {
                run = false;
            } else {
                System.out.println(START);
            }
        }
    }
}
