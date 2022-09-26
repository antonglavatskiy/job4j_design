package ru.job4j.ood.lsp;

public class LimitedAccount extends Account {
    private double balance;

    public LimitedAccount(double balance) {
        super(balance);
    }

    /*
    1)
    Усиление предусловий в подклассе
     */
    @Override
    public boolean pay(double sum) {
        if (balance < 0 || sum > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        if (sum > 10000) {
            throw new IllegalArgumentException("Rejected");
        }
        return true;
    }

    /*
    2)
    Постусловия ослаблены в подклассе
     */
    @Override
    public void commission() {
        balance -= balance * 0.01;
    }
}

