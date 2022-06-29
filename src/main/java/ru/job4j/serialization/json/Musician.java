package ru.job4j.serialization.json;

import java.util.Arrays;

public class Musician {
    private boolean singer;
    private String name;
    private int age;
    private Group group;
    private String[] instrumentalSkills;

    public Musician(boolean singer, String name, int age, Group group, String[] instrumentalSkills) {
        this.singer = singer;
        this.name = name;
        this.age = age;
        this.group = group;
        this.instrumentalSkills = instrumentalSkills;
    }

    public boolean isSinger() {
        return singer;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Group getGroup() {
        return group;
    }

    public String[] getInstrumentalSkills() {
        return instrumentalSkills;
    }

    @Override
    public String toString() {
        return "Musician{"
                + "singer=" + singer
                + ", name='" + name + '\''
                + ", age=" + age
                + ", group=" + group
                + ", instrumentalSkills=" + Arrays.toString(instrumentalSkills)
                + '}';
    }
}
