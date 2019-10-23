package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.entity.UserStorageRole;

public class UserStorageRelationsCreationDto {
    private Long userId;
    @JsonProperty("userRole")
    private UserStorageRole userRole;

    public UserStorageRelations getRelations() {
        UserStorageRelations relations = new UserStorageRelations();
        relations.setUserRole(userRole);
        switch (userRole) {
            case READER:
                relations.setIncludedInUserStatistics(false);
                break;
            case CREATOR:
            case ADMIN:
            case EDITOR:
                relations.setIncludedInUserStatistics(true);
                break;
            default:
                throw new RuntimeException("No handler for UserStorageRole " + userRole.getValue());
        }
        return relations;
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
