package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private static final double ABS = 1;
    private List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        double percent = expiryCalculate(food);
        if (percent >= ABS) {
            foods.add(food);
            rsl = true;
        }
        return rsl;
    }
}
