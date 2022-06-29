package ru.job4j.serialization.json;

public class Group {
    private String name;
    private String genre;

    public Group(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Group{"
                + "name='" + name + '\''
                + ", genre='" + genre + '\''
                + '}';
    }
}
