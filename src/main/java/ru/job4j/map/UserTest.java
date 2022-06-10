package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {
    public static void main(String[] args) {
        Calendar userBirthday = new GregorianCalendar(1990, 12, 31);
        User user1 = new User("User1", 1, userBirthday);
        User user2 = new User("User1", 1, userBirthday);
        System.out.println("user1: " + user1);
        System.out.println("user2: " + user2);
        System.out.println(bucket(user1) == bucket(user2));
        Map<User, Object> users = new HashMap<>();
        users.put(user1, new Object());
        System.out.println("value1: " + users.get(user1));
        users.put(user2, new Object());
        for (Map.Entry<User, Object> user : users.entrySet()) {
            System.out.println(user);
        }
    }

    private static int bucket(Object key) {
        int hashCode = (key == null) ? 0 : key.hashCode();
        int hash = hashCode ^ (hashCode >>> 16);
        return 15 & hash;
    }
}
