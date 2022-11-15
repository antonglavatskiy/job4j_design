package ru.job4j.design.lsp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    @Test
    public void whenDistributeFoodThenAddTrash() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, -2);
        createDate.add(Calendar.DAY_OF_MONTH, -20);
        Food tomato = new Vegetable("Tomato", expiryDate, createDate, 75.99, 0.8);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(tomato);
        assertThat(shop.getFoods().size()).isEqualTo(0);
        assertThat(warehouse.getFoods().size()).isEqualTo(0);
        assertThat(trash.getFoods().get(0).getName()).isEqualTo("Tomato");
    }

    @Test
    public void whenDistributeFoodThenAddWarehouse() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 15);
        createDate.add(Calendar.DAY_OF_MONTH, -2);
        Food apple = new Fruit("Apple", expiryDate, createDate, 190.50, 0.8);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(apple);
        assertThat(shop.getFoods().size()).isEqualTo(0);
        assertThat(warehouse.getFoods().get(0).getName()).isEqualTo("Apple");
        assertThat(trash.getFoods().size()).isEqualTo(0);
    }

    @Test
    public void whenDistributeFoodThenAddShop() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 10);
        createDate.add(Calendar.DAY_OF_MONTH, -5);
        Food peach = new Fruit("Peach", expiryDate, createDate, 120.89, 0.8);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(peach);
        assertThat(shop.getFoods().get(0).getName()).isEqualTo("Peach");
        assertThat(warehouse.getFoods().size()).isEqualTo(0);
        assertThat(trash.getFoods().size()).isEqualTo(0);
    }

    @Test
    public void whenDistributeFoodThenAddShopWithChangePrice() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 2);
        createDate.add(Calendar.DAY_OF_MONTH, -15);
        double price = 30.45;
        double discount = 0.8;
        Food potato = new Vegetable("Potato", expiryDate, createDate, price, discount);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(potato);
        assertThat(shop.getFoods().get(0).getPrice())
                .isLessThan(price)
                .isEqualTo(price * discount);
    }

    @Test
    public void whenDistributeSeveralFoods() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, -2);
        createDate.add(Calendar.DAY_OF_MONTH, -20);
        Food tomato = new Vegetable("Tomato", expiryDate, createDate, 75.99, 0.8);
        controlQuality.distribution(tomato);
        expiryDate.add(Calendar.DAY_OF_MONTH, 17);
        createDate.add(Calendar.DAY_OF_MONTH, 18);
        Food apple = new Fruit("Apple", expiryDate, createDate, 190.50, 0.8);
        controlQuality.distribution(apple);
        expiryDate.add(Calendar.DAY_OF_MONTH, -5);
        createDate.add(Calendar.DAY_OF_MONTH, -3);
        Food peach = new Fruit("Peach", expiryDate, createDate, 120.89, 0.8);
        controlQuality.distribution(peach);
        expiryDate.add(Calendar.DAY_OF_MONTH, -8);
        createDate.add(Calendar.DAY_OF_MONTH, -10);
        Food potato = new Vegetable("Potato", expiryDate, createDate, 30.45, 0.8);
        controlQuality.distribution(potato);
        assertThat(shop.getFoods().size()).isEqualTo(2);
        assertThat(warehouse.getFoods().size()).isEqualTo(1);
        assertThat(trash.getFoods().size()).isEqualTo(1);
    }

    @Test
    public void whenDistributeFoodsAndResortWithChange() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 10);
        createDate.add(Calendar.DAY_OF_MONTH, -5);
        Food peach = new Fruit("Peach", expiryDate, createDate, 120.89, 0.8);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(peach);
        assertThat(shop.getFoods().get(0).getName()).isEqualTo("Peach");
        assertThat(warehouse.getFoods().size()).isEqualTo(0);
        assertThat(trash.getFoods().size()).isEqualTo(0);
        expiryDate.add(Calendar.DAY_OF_MONTH, -11);
        peach.setExpiryDate(expiryDate);
        controlQuality.resort();
        assertThat(trash.getFoods().get(0).getName()).isEqualTo("Peach");
        assertThat(warehouse.getFoods().size()).isEqualTo(0);
        assertThat(shop.getFoods().size()).isEqualTo(0);
    }

    @Test
    public void whenDistributeFoodsAndResortWithoutChanges() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, warehouse, trash);
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DAY_OF_MONTH, 10);
        createDate.add(Calendar.DAY_OF_MONTH, -5);
        Food peach = new Fruit("Peach", expiryDate, createDate, 120.89, 0.8);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.distribution(peach);
        assertThat(shop.getFoods().get(0).getName()).isEqualTo("Peach");
        assertThat(warehouse.getFoods().size()).isEqualTo(0);
        assertThat(trash.getFoods().size()).isEqualTo(0);
        controlQuality.resort();
        assertThat(shop.getFoods().get(0).getName()).isEqualTo("Peach");
        assertThat(warehouse.getFoods().size()).isEqualTo(0);
        assertThat(trash.getFoods().size()).isEqualTo(0);
    }
}