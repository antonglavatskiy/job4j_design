package ru.job4j.design.lsp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    @Test
    public void whenDistributeFoodThenAddTrash() {
        List<Store> stores = List.of(new Shop(), new Warehouse(), new Trash());
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, -2);
        createDate.add(Calendar.DAY_OF_MONTH, -20);
        Food tomato = new Vegetable("Tomato", expiryDate, createDate, 75.99, 0.8);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(tomato);
        assertThat(stores.get(0).getFoods().size()).isEqualTo(0);
        assertThat(stores.get(1).getFoods().size()).isEqualTo(0);
        assertThat(stores.get(2).getFoods().get(0).getName()).isEqualTo("Tomato");
    }

    @Test
    public void whenDistributeFoodThenAddWarehouse() {
        List<Store> stores = List.of(new Shop(), new Warehouse(), new Trash());
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 15);
        createDate.add(Calendar.DAY_OF_MONTH, -2);
        Food apple = new Fruit("Apple", expiryDate, createDate, 190.50, 0.8);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(apple);
        assertThat(stores.get(0).getFoods().size()).isEqualTo(0);
        assertThat(stores.get(1).getFoods().get(0).getName()).isEqualTo("Apple");
        assertThat(stores.get(2).getFoods().size()).isEqualTo(0);
    }

    @Test
    public void whenDistributeFoodThenAddShop() {
        List<Store> stores = List.of(new Shop(), new Warehouse(), new Trash());
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 10);
        createDate.add(Calendar.DAY_OF_MONTH, -5);
        Food peach = new Fruit("Peach", expiryDate, createDate, 120.89, 0.8);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(peach);
        assertThat(stores.get(0).getFoods().get(0).getName()).isEqualTo("Peach");
        assertThat(stores.get(1).getFoods().size()).isEqualTo(0);
        assertThat(stores.get(2).getFoods().size()).isEqualTo(0);
    }

    @Test
    public void whenDistributeFoodThenAddShopWithChangePrice() {
        List<Store> stores = List.of(new Shop(), new Warehouse(), new Trash());
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 2);
        createDate.add(Calendar.DAY_OF_MONTH, -15);
        double price = 30.45;
        double discount = 0.8;
        Food potato = new Vegetable("Potato", expiryDate, createDate, price, discount);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(potato);
        assertThat(stores.get(0).getFoods().get(0).getPrice())
                .isLessThan(price)
                .isEqualTo(price * discount);
    }
}