package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.UserStorageRole;

public class StorageRelationsPatchesDto {
    private UserStorageRole userRole;

    public UserStorageRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserStorageRole userRole) {
        this.userRole = userRole;
    }
}
