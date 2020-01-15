package com.godev.budgetgo.exception;

import org.springframework.security.access.AccessDeniedException;

public class StorageAccessDeniedException extends AccessDeniedException {

    public StorageAccessDeniedException() {
        super("Storage access denied");
    }

    public StorageAccessDeniedException(String message) {
        super(message);
    }

    public StorageAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
