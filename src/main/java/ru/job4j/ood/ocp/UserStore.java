package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.List;

public class UserStore {
    private final List<User> store = new ArrayList<>();

    public void add(User user) {
        if (user.getAge() > 18 && user.getAge() < 25) {
            user.setName("User");
            store.add(user);
        }
    }
    /*
    2)
    Если появится необходимость добавления критериев, то изменяется
    класс, что является нарушением ОСР.
     */
}
