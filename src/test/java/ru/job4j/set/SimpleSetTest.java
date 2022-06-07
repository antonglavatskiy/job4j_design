package ru.job4j.set;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenEmptySet() {
        Set<Integer> set = new SimpleSet<>();
        assertFalse(set.contains(null));
        assertFalse(set.contains(2));
    }

    @Test
    public void whenUniqueAddSet() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Assert.assertEquals(3, set.size());
    }

    @Test
    public void whenAddSet() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        set.add(3);
        Assert.assertEquals(2, set.size());
    }
}