package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutCommentsAndWithoutEmptyLines() {
        String path = "./data/pair_without_comment_and_without_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("username"));
        assertThat(config.value("password"), is("1234567"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithoutCommentsAndWithEmptyLines() {
        String path = "./data/pair_without_comment_and_with_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("username1"));
        assertThat(config.value("password"), is("567"));
        assertThat(config.value("id"), is("1"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentsAndWithEmptyLines() {
        String path = "./data/pair_with_comment_and_with_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("seventy seven"));
        assertThat(config.value("password"), is("six"));
        assertThat(config.value("id"), is("77"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithTwoEquals() {
        String path = "./data/pair_with_two_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("id"), is("33"));
        assertThat(config.value("user"), is("username=Ivan"));
        assertThat(config.value("password"), is("word=good="));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithBeginEqualsSign() {
        String path = "./data/pair_with_begin_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithEndEqualsSign() {
        String path = "./data/pair_with_end_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithOnlyEqualsSign() {
        String path = "./data/pair_with_only_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutEqualsSign() {
        String path = "./data/pair_without_equals.properties";
        Config config = new Config(path);
        config.load();
    }
}