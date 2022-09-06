package ru.job4j.ood.srp;

public interface Order {
    int findById();

    void saveOrder();

    /*
    Интерфейс описывает поведение - поиск и сохранение заказа, что является нарушением SRP.
    Поведение следует разделить на два отдельных интерфейса.
     */

}
