package ru.job4j.design.lsp;

import java.util.Calendar;
import java.util.List;

public interface Store {

    boolean add(Food food);

    List<Food> getFoods();

    default double expiryCalculate(Food food) {
        return (double) (Calendar.getInstance().getTime().getTime() - food.getCreateDate().getTime().getTime())
                / (food.getExpiryDate().getTime().getTime() - food.getCreateDate().getTime().getTime());
    }
}
