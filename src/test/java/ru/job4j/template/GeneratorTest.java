package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {
    @Test
    public void whenValidProduce() {
        Generator generator = new TextGenerator();
        Map<String, String> map = Map.of(
                "name", "admin",
                "subject", "user"
        );
        String template = "I am a ${name}, Who are ${subject}?";
        String result = "I am a admin, Who are user?";
        assertThat(result).isEqualTo(generator.produce(template, map));
    }

    @Test
    public void whenInvalidTemplate() {
        Generator generator = new TextGenerator();
        Map<String, String> map = Map.of(
                "name", "admin",
                "subject", "user"
        );
        String template = "I am a ${name}, Who are you?";
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("template is invalid");
    }

    @Test
    public void whenEmptyArgs() {
        Generator generator = new TextGenerator();
        Map<String, String> map = Map.of();
        String template = "I am a ${name}, Who are ${subject}?";
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("arguments is empty");
    }

    @Test
    public void whenInvalidKeyOfTemplate() {
        Generator generator = new TextGenerator();
        Map<String, String> map = Map.of(
                "name", "admin",
                "subject", "user"
        );
        String template = "I am a ${name}, Who are ${you}?";
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("wrong template key");
    }

    @Test
    public void whenArgsHaveExtraKeys() {
        Generator generator = new TextGenerator();
        Map<String, String> map = Map.of(
                "name", "admin",
                "subject", "user",
                "age", "100"
        );
        String template = "I am a ${name}, Who are ${subject}?";
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("arguments have extra keys");
    }
}