package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribution(Food food) {
        for (Store store : stores) {
            if (store.add(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> foodList = new ArrayList<>();
        for (Store store : stores) {
            foodList.addAll(store.getFoods());
            store.clearStore();
        }
        for (Food food : foodList) {
            distribution(food);
        }
    }
}
