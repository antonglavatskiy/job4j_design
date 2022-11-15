package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private static final double UP_LIMIT = 0.25;
    private List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> getFoods() {
        return List.copyOf(foods);
    }

    @Override
    public void clearStore() {
        foods.clear();
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        double percent = expiryCalculate(food);
        if (percent < UP_LIMIT) {
            foods.add(food);
            rsl = true;
        }
        return rsl;
    }
}
