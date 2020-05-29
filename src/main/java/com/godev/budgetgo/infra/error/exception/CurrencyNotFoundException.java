package com.godev.budgetgo.infra.error.exception;

public class CurrencyNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Currency not found";

    public CurrencyNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public CurrencyNotFoundException(String message) {
        super(message);
    }

    public static CurrencyNotFoundException byId(Long id) {
        return new CurrencyNotFoundException("Currency not found by id = " + id);
    }
}
