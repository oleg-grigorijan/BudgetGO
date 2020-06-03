package com.godev.budgetgo.infra.error.exception;

import com.godev.budgetgo.domain.operation.StorageOperationKey;

public class OperationNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Operation not found";

    public OperationNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public OperationNotFoundException(String message) {
        super(message);
    }

    public static OperationNotFoundException byId(StorageOperationKey id) {
        return new OperationNotFoundException("Operation not found by id = " + id.getOperationId()
                + " in storage with id = " + id.getStorageId());
    }
}
