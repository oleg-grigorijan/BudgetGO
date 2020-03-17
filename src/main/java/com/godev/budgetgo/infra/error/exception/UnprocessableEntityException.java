package com.godev.budgetgo.infra.error.exception;

public class UnprocessableEntityException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Unprocessable entity";

    public UnprocessableEntityException() {
        this(DEFAULT_MESSAGE);
    }

    public UnprocessableEntityException(String message) {
        super(message);
    }

    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnprocessableEntityException(Throwable cause) {
        this(DEFAULT_MESSAGE, cause);
    }
}
