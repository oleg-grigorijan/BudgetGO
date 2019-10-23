package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.entity.UserStorageRole;

public class UserStorageRelationsPatchDto {
    @JsonProperty("userRole")
    private UserStorageRole userRole;
    @JsonProperty("isIncludedInUserStatistics")
    private Boolean includedInUserStatistics;

    public void applyPatchesTo(UserStorageRelations relations) {
        if (userRole != null) relations.setUserRole(userRole);
        if (includedInUserStatistics != null) relations.setIncludedInUserStatistics(includedInUserStatistics);
    }

    public UserStorageRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserStorageRole userRole) {
        this.userRole = userRole;
    }

    public Boolean isIncludedInUserStatistics() {
        return includedInUserStatistics;
    }

    public void setIncludedInUserStatistics(Boolean includedInUserStatistics) {
        this.includedInUserStatistics = includedInUserStatistics;
    }
}
