package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private static final double UP_LIMIT = 0.75;
    private static final double LOW_LIMIT = 0.25;
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
        if (percent >= LOW_LIMIT && percent <= UP_LIMIT) {
            foods.add(food);
            rsl = true;
        }
        if (percent > UP_LIMIT && percent < ABS) {
            double newPrice = food.getPrice() * food.getDiscount();
            food.setPrice(newPrice);
            foods.add(food);
            rsl = true;
        }
        return rsl;
    }
}
