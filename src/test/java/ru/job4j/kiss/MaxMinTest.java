package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    @Test
    public void findMax() {
        Comparator<String> comparator = Comparator.comparingInt(String::length);
        assertThat(new MaxMin().max(List.of("one", "two", "three"), comparator)).isEqualTo("three");
    }

    @Test
    public void findMin() {
        Comparator<String> comparator = String::compareTo;
        assertThat(new MaxMin().min(List.of("one", "two", "three"), comparator)).isEqualTo("one");
    }

    @Test
    public void whenEmptyList() {
        Comparator<Integer> comparator = Integer::compareTo;
        assertThatThrownBy(() -> new MaxMin().min(List.of(), comparator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }
}