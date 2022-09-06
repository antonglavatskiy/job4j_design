package ru.job4j.ood.srp;

public interface OrderStore {
    boolean deleteOrder();

    boolean saveOrder();

    String analyzeOrder();

     /*
     Интерфейс описывает поведение - удаление и сохранение заказа. Анализ заказа является нарушением SRP.
     Анализ следует выделить в отдельный класс.
      */
}
