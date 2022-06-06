package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements LinkedList<E> {
    Node<E> first;
    Node<E> last;
    private int modCount;
    private int size;

    @Override
    public void add(E value) {
        modCount++;
        Node<E> current = last;
        Node<E> newNode = new Node<>(current, value, null);
        last = newNode;
        if (current == null) {
            first = newNode;
        } else {
            current.next = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private Node<E> node = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = node.value;
                node = node.next;
                return result;
            }
        };
    }
}
