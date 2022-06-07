package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    public T poll() {
        if (input.isEmpty() && output.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.pop();
    }

    public void push(T value) {
        input.push(value);

    }
}
