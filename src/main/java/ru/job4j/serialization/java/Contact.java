package ru.job4j.serialization.java;

import java.io.Serializable;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phoneNumber;

    public Contact(int zipCode, String phoneNumber) {
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phoneNumber='" + phoneNumber + '\''
                + '}';
    }
}
