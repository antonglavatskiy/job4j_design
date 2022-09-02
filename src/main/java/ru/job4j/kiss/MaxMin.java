package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> maxEl = (t1, t2) -> comparator.compare(t1, t2) > 0;
        return findElement(value, maxEl);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> minEl = (t1, t2) -> comparator.compare(t1, t2) < 0;
        return findElement(value, minEl);
    }

    private <T> T findElement(List<T> list, BiPredicate<T, T> predicate) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }
        T result = list.get(0);
        for (T el : list) {
            if (predicate.test(el, result)) {
                result = el;
            }
        }
        return result;
    }
}
