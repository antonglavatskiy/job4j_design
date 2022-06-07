package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        boolean result = false;
        if (!contains(value)) {
            set.add(value);
            result = true;
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(value, iterator.next())) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
