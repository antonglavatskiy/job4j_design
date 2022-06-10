package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int size = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int hashCode = key.hashCode();
        int index = indexFor(hash(hashCode));
        if (size / LOAD_FACTOR == table.length) {
            expand();
        }
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            size++;
            result = true;
        } else if (table[index].key.hashCode() == hashCode) {
            table[index].value = value;
            result = true;
        }
        return result;
    }

    @Override
    public V get(K key) {
        V result = null;
        int hashCode = key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] != null) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int hashCode = key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] != null) {
            table[index] = null;
            modCount++;
            size--;
            result = true;
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (count != table.length && table[count] == null) {
                    count++;
                }
                return table.length > count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[count++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private void expand() {
        table = Arrays.copyOf(table, table.length * 2);
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
