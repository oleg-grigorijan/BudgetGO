package com.godev.budgetgo.exception;

public class BadRequestException extends RuntimeException {

    private final static String DEFAULT_MESSAGE = "Bad request";

    public BadRequestException() {
        this(DEFAULT_MESSAGE);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
