package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.UserStorageRole;

import javax.validation.constraints.NotNull;

public class StorageRelationsCreationDto {

    private Long storageId;

    @NotNull
    private Long userId;

    @NotNull
    private UserStorageRole userRole;

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

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
