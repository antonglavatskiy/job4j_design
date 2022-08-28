package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void whenLoadWithoutExceptions() {
        NameLoad load = new NameLoad();
        String[] arr = {"1=one", "2=two", "3=three"};
        load.parse(arr);
        Map<String, String> result = load.getMap();
        Map<String, String> expected = Map.of(
                "1", "one",
                "2", "two",
                "3", "three");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void checkEmpty() {
        NameLoad load = new NameLoad();
        assertThatThrownBy(load::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }
    @Test
    void whenArrayIsEmpty() {
        NameLoad load = new NameLoad();
        assertThatThrownBy(load::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void whenElementDoesNotHaveAnEqualsSign() {
        NameLoad load = new NameLoad();
        String element = "1-one";
        assertThatThrownBy(() -> load.parse(element))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(element)
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    void whenElementDoesNotHaveKey() {
        NameLoad load = new NameLoad();
        String element = "=two";
        assertThatThrownBy(() -> load.parse(element))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(element)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void whenElementDoesNotHaveValue() {
        NameLoad load = new NameLoad();
        String element = "3=";
        assertThatThrownBy(() -> load.parse(element))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(element)
                .hasMessageContaining("does not contain a value");
    }
}