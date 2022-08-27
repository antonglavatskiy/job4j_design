package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(10, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .isEqualTo("Unknown object");
    }

    @Test
    void whenBoxHas4Vertices() {
        Box box = new Box(4, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices)
                .isEven()
                .isGreaterThan(0)
                .isEqualTo(4);
    }

    @Test
    void whenBoxHas10Vertices() {
        Box box = new Box(10, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices)
                .isNegative()
                .isOdd().isEqualTo(-1);
    }

    @Test
    void whenBoxHas6VerticesThanNotExist() {
        Box box = new Box(6, 6);
        boolean isExist = box.isExist();
        assertThat(isExist)
                .isFalse()
                .isEqualTo(false);
    }

    @Test
    void whenBoxHas8VerticesThanExist() {
        Box box = new Box(8, 8);
        boolean isExist = box.isExist();
        assertThat(isExist)
                .isTrue()
                .isEqualTo(true);
    }

    @Test
    void whenBoxHas4VerticesAnd2EdgeThanAreaIs6dot92() {
        Box box = new Box(4, 2);
        double area = box.getArea();
        assertThat(area)
                .isNotNegative()
                .isLessThan(10d)
                .isCloseTo(6.92d, withPrecision(0.01d));
    }

    @Test
    void whenBoxHas8VerticesAnd2EdgeThanAreaIs24() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area)
                .isNotNegative()
                .isGreaterThan(20d)
                .isCloseTo(24d, withPrecision(0.01d));
    }

}