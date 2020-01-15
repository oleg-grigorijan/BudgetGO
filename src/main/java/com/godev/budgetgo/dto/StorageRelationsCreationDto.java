package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.UserStorageRole;

import javax.validation.constraints.NotNull;

public class StorageRelationsCreationDto {

    @NotNull
    private Long userId;

    @NotNull
    private UserStorageRole userRole;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserStorageRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserStorageRole userRole) {
        this.userRole = userRole;
    }
}
