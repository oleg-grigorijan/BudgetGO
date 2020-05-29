package com.godev.budgetgo.infra.error.exception;

import com.godev.budgetgo.domain.storage.UserStorageKey;

public class StorageRelationsNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Storage relations not found";

    public StorageRelationsNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public StorageRelationsNotFoundException(String message) {
        super(message);
    }

    public static StorageRelationsNotFoundException byId(UserStorageKey id) {
        return new StorageRelationsNotFoundException("Storage relations not found for user with id = " + id.getUserId()
                + " in storage with id = " + id.getStorageId());
    }
}
