package com.godev.budgetgo.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStorageRole {
    CREATOR("CREATOR"),
    ADMIN("ADMIN"),
    EDITOR("EDITOR"),
    READER("READER");

    private final String value;

    UserStorageRole(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}