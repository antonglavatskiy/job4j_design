package ru.job4j.design.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    public static final String TAB = "----";

    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> {
            String number = menuItemInfo.getNumber();
            String name = menuItemInfo.getName();
            int count = menuItemInfo.getNumber().split("\\.").length;
            System.out.println(TAB.repeat(--count) + number + name);
        });
    }
}
