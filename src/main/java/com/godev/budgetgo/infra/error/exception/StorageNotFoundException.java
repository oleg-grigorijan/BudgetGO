package com.godev.budgetgo.infra.error.exception;

public class StorageNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Storage not found";

    public StorageNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public StorageNotFoundException(String message) {
        super(message);
    }

    public static StorageNotFoundException byId(Long id) {
        return new StorageNotFoundException("Storage not found by id = " + id);
    }
}
