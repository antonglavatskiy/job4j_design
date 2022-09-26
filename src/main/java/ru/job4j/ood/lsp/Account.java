package ru.job4j.ood.lsp;

public class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public boolean pay(double sum) {
        if (balance < 0 || sum > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        return true;
    }

    public void commission() {
        if (balance > 1000) {
            balance -= balance * 0.01;
        }
    }
}
