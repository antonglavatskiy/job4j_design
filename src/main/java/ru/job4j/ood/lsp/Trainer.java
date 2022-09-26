package ru.job4j.ood.lsp;

public class Trainer {
    private User user;

    public Trainer(User user) {
        validate(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        validate(user);
        this.user = user;
    }

    private void validate(User user) {
        if (user.getWeight() < 50) {
            throw new IllegalArgumentException("User is small");
        }
    }
}
