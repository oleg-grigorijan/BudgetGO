package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.UserStorageRole;

import java.util.Optional;

public class StorageRelationsPatchesDto {
    private UserStorageRole userRole;

    public Optional<UserStorageRole> getUserRole() {
        return Optional.ofNullable(userRole);
    }

    public void setUserRole(UserStorageRole userRole) {
        this.userRole = userRole;
    }
}
