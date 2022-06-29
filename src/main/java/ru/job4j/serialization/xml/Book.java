package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    @XmlAttribute
    private boolean digital;
    @XmlElement(name = "author")
    private Author chiefAuthor;
    private String title;
    @XmlAttribute
    private double price;
    @XmlElementWrapper(name = "publishings")
    @XmlElement(name = "publishing")
    private String[] publishingList;

    public Book() {
    }

    public Book(boolean digital, Author chiefAuthor, String title, double price, String[] publishingList) {
        this.digital = digital;
        this.chiefAuthor = chiefAuthor;
        this.title = title;
        this.price = price;
        this.publishingList = publishingList;
    }

    public boolean isDigital() {
        return digital;
    }

    public Author getChiefAuthor() {
        return chiefAuthor;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String[] getPublishingList() {
        return publishingList;
    }

    @Override
    public String toString() {
        return "Book{"
                + "digital=" + digital
                + ", chiefAuthor=" + chiefAuthor
                + ", title='" + title + '\''
                + ", price=" + price
                + ", publishingList=" + Arrays.toString(publishingList)
                + '}';
    }
}
