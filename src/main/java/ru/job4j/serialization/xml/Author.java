package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {
    @XmlAttribute
    private boolean sex;
    private String name;
    @XmlAttribute
    private int age;

    public Author() {
    }

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
