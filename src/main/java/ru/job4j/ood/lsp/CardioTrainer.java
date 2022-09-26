package ru.job4j.ood.lsp;

public class CardioTrainer extends Trainer {
    private User user;

    public CardioTrainer(User user) {
        super(user);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}

/*
3)
Условия базового класса не сохранены в подклассе - нет проверки пользователя на вес.
 */
