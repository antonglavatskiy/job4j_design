package ru.job4j.serialization.xml;

public class Author {
    private boolean sex;
    private String name;
    private int age;

    public Author(boolean sex, String name, int age) {
        this.sex = sex;
        this.name = name;
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Author{"
                + "sex=" + sex
                + ", name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}
