package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRole;

public class UserStorageRelationsPatchDto {
    @JsonProperty("userRole")
    private UserStorageRole userStorageRole;
    @JsonProperty("isIncludedInUserStatistics")
    private Boolean includedInUserStatistics;

    public UserStorageRole getUserStorageRole() {
        return userStorageRole;
    }

    public void setUserStorageRole(UserStorageRole userStorageRole) {
        this.userStorageRole = userStorageRole;
    }

    public Boolean getIncludedInUserStatistics() {
        return includedInUserStatistics;
    }

    public void setIncludedInUserStatistics(Boolean includedInUserStatistics) {
        this.includedInUserStatistics = includedInUserStatistics;
    }
}
