package ru.job4j.ood.srp;

public class Article {
    private StringBuilder text;

    public Article(StringBuilder text) {
        this.text = text;
    }

    public StringBuilder getText() {
        return text;
    }

    public void append(String text) {
        if (this.text != null) {
            this.text.append(text);
        }
    }

    public void print() {
        System.out.println(text);
    }

    /*
    Наличие метода, который печатает текст в классе Article, нарушает SRP.
    Необходимо создать еще один класс, который будет обрабатывать только печать текста.
     */
}
