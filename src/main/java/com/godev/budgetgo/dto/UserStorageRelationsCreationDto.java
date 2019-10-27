package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRole;

public class UserStorageRelationsCreationDto {
    private Long storageId;
    private Long userId;
    @JsonProperty("userRole")
    private UserStorageRole userStorageRole;

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

    public UserStorageRole getUserStorageRole() {
        return userStorageRole;
    }

    public void setUserStorageRole(UserStorageRole userStorageRole) {
        this.userStorageRole = userStorageRole;
    }
}
