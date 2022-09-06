package ru.job4j.ood.srp;

import java.util.List;

public class User {
    private int id;
    private String userName;
    private String password;
    private List<User> list;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void add(User user) {
        if (checkPassword(user.userName)) {
            list.add(user);
        }
    }

    public User remove(User user) {
        return list.remove(user) ? user : null;
    }

    private boolean checkPassword(String password) {
        return password.length() > 3;
    }

    /*
    Нарушение SRP - методы добавления и удаления.
    Класс User описывает модель данных, поэтому хранилище можно описать отдельным интерфейсом,
    а List<User> и методы добавления и удаления необходимо перенести в отдельный класс.
     */

}
