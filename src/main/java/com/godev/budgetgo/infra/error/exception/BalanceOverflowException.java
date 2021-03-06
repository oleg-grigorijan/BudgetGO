package com.godev.budgetgo.infra.error.exception;

import com.godev.budgetgo.domain.storage.Storage;

public class BalanceOverflowException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Storage balance overflow";

    public BalanceOverflowException() {
        super(DEFAULT_MESSAGE);
    }

    public BalanceOverflowException(String message) {
        super(message);
    }

    public static BalanceOverflowException ofStorage(Storage storage) {
        return new BalanceOverflowException("Balance overflow of storage with id = " + storage.getId());
    }
}
