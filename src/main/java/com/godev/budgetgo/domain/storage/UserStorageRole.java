package com.godev.budgetgo.domain.storage;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStorageRole {

    CREATOR("CREATOR"),
    ADMIN("ADMIN"),
    EDITOR("EDITOR"),
    VIEWER("VIEWER");

    private final String value;

    UserStorageRole(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
