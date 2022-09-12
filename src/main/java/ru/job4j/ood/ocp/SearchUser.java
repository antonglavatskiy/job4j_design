package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchUser {
    public static List<User> search(List<User> list, String name) {
        List<User> rsl = new ArrayList<>();
        for (User user : list) {
            if (name.equals(user.getName())) {
                rsl.add(user);
            }
        }
        return rsl;
    }
    /*
    1)
    Для расширения функциональности, например поиску по возрасту,
    приходится изменять класс, что ведет к нарушению ОСР.
    В методе можно использовать Predicate.
     */
}
