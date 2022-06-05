package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new  Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T value) {
        T rsl = get(index);
        container[index] = value;
        return rsl;
    }

    @Override
    public T remove(int index) {
        modCount++;
        T rsl = get(index);
        System.arraycopy(container, index + 1,
                container, index, container.length - 1 - index);
        container[container.length - 1] = null;
        size--;
        return rsl;
    }

    @Override
    public T get(int index) {
        int indexRsl = Objects.checkIndex(index, size);
        return container[indexRsl];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return size > count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[count++];
            }
        };
    }

    private T[] grow() {
        return Arrays.copyOf(container, container.length * 2);
    }
}
