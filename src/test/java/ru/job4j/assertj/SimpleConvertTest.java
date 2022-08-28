package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("dog", "cat", "mouse", "cow", "goat");
        assertThat(list).hasSize(5)
                .contains("cat")
                .contains("cow", Index.atIndex(3))
                .containsAnyOf("cat", "snake", "shark")
                .allMatch(str -> str.length() > 2)
                .endsWith("goat")
                .first().isEqualTo("dog");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("Ivan", "Andrey", "Ruslan", "Sergey");
        assertThat(set).hasSize(4)
                .doesNotContainNull()
                .doesNotContain("Bob")
                .contains("Ivan")
                .anyMatch("Andrey"::equals)
                .isNotEmpty();
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap(
                 "zero", "one", "two", "three");
        assertThat(map).hasSize(4)
                .containsKeys("zero", "three")
                .containsValues(2, 1)
                .doesNotContainKey("five")
                .doesNotContainValue(75)
                .containsEntry("two", 2)
                .doesNotContainEntry("first", 1);
    }
}