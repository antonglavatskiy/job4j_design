package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();
    private int size;

    public T pop() {
        size--;
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
