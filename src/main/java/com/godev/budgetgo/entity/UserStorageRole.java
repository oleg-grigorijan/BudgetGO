package com.godev.budgetgo.entity;

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

    public boolean canModifyStorage() {
        return this != VIEWER;
    }

    public boolean canBeCreatedBy(UserStorageRole anotherRole) {
        return this != CREATOR && (anotherRole == CREATOR || anotherRole == ADMIN);
    }

    public boolean canBeModifiedBy(UserStorageRole anotherRole) {
        return anotherRole == CREATOR || (anotherRole == ADMIN && this != CREATOR);
    }

    public boolean getDefaultIncludedInUserStatistics() {
        return this != VIEWER;
    }
}