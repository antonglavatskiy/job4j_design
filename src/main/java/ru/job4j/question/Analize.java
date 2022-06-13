package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<User, Info> infoMap = new HashMap<>();
        Map<Integer, String> usersPrev = new HashMap<>();
        Map<Integer, String> usersCurr = new HashMap<>();
        int added = 0;
        int changed = 0;
        int deleted = 0;
        for (User user : previous) {
            usersPrev.put(user.getId(), user.getName());
        }
        for (User user : current) {
            usersCurr.put(user.getId(), user.getName());
        }
        for (User user : previous) {
            if (!usersCurr.containsKey(user.getId())) {
                deleted++;
            }
        }
        for (User user : current) {
            if (!usersPrev.containsKey(user.getId())) {
                infoMap.put(user, new Info(1, 0, 0));
            }
            if (usersPrev.containsKey(user.getId()) && !usersPrev.get(user.getId()).equals(user.getName())) {
                infoMap.put(user, new Info(0, 1, 0));
            }
        }
        for (Map.Entry<User, Info> pair : infoMap.entrySet()) {
            added += pair.getValue().getAdded();
            changed += pair.getValue().getChanged();
        }
        return new Info(added, changed, deleted);
    }
}
