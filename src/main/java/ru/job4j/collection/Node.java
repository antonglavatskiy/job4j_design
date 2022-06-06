package ru.job4j.collection;

public class Node<E> {
    E value;
    Node<E> next;
    Node<E> prev;

    public Node(Node<E> prev, E value, Node<E> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
