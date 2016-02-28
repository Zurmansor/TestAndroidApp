package com.shyrokovkaya.anastasiia.testapplication.validator;

public enum ErrorMessage {
    INVALID_NAME("name is not valid"),
    INVALID_EMAIL("email is not valid"),
    INVALID_PHONE("phone number is not valid"),
    INVALID_URL("url address is not valid");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
