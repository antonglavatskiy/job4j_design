package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    @Test
    public void whenAddKeyAndExpandMapThenGetValue() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        simpleMap.put(3, "three");
        simpleMap.put(4, "four");
        simpleMap.put(5, "five");
        simpleMap.put(6, "six");
        simpleMap.put(2, "six");
        simpleMap.put(7, "seven");
        simpleMap.put(8, "eight");
        simpleMap.put(5, "eight");
        simpleMap.put(9, "nine");
        simpleMap.put(10, "ten");
        Assert.assertEquals(10, simpleMap.size());
        Assert.assertEquals("five", simpleMap.get(5));
        Assert.assertEquals("two", simpleMap.get(2));
    }

    @Test
    public void whenAddUniqueKeyThenGetSize() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        int expected = 2;
        Assert.assertEquals(expected, simpleMap.size());
    }

    @Test
    public void whenAddKeyThenGetSize() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(1, "two");
        int expected = 1;
        Assert.assertEquals(expected, simpleMap.size());
    }

    @Test
    public void whenAddUniqueKeyAndThenGetKey() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        String expected = "one";
        Assert.assertEquals(expected, simpleMap.get(1));
    }

    @Test
    public void whenAddUniqueKeyAndThenGetAbsentKey() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        Assert.assertNull(simpleMap.get(3));
    }

    @Test
    public void whenAddKeyAndThenGetFalse() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        Assert.assertFalse(simpleMap.put(1, "two"));
    }

    @Test
    public void whenAddKeyAndRemoveThenGetSize() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        simpleMap.remove(1);
        int expected = 1;
        Assert.assertEquals(expected, simpleMap.size());
    }

    @Test
    public void whenAddKeyAndRemoveThenGetKey() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        simpleMap.remove(1);
        String expected = "two";
        Assert.assertEquals(expected, simpleMap.get(2));
    }

    @Test
    public void whenAddKeyAndRemoveThenGetAbsentKey() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        simpleMap.remove(1);
        Assert.assertNull(simpleMap.get(1));
    }

    @Test
    public void whenGetIteratorFromEmptyMapThenHasNextReturnFalse() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        Assert.assertFalse(simpleMap.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        Assert.assertEquals(Integer.valueOf(1), simpleMap.iterator().next());
        Assert.assertEquals(Integer.valueOf(1), simpleMap.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        Iterator<Integer> iterator = simpleMap.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        Iterator<Integer> iterator = simpleMap.iterator();
        simpleMap.put(2, "two");
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "one");
        simpleMap.put(2, "two");
        Iterator<Integer> iterator = simpleMap.iterator();
        simpleMap.remove(1);
        iterator.next();
    }
}