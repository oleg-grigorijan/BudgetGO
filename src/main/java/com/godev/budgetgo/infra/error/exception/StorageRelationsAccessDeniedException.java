package com.godev.budgetgo.infra.error.exception;

import org.springframework.security.access.AccessDeniedException;

public class StorageRelationsAccessDeniedException extends AccessDeniedException {

    public StorageRelationsAccessDeniedException() {
        super("Storage relations access denied");
    }

    public StorageRelationsAccessDeniedException(String message) {
        super(message);
    }

    public StorageRelationsAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
