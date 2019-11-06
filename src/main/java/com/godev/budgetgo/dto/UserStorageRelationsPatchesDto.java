package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRole;

public class UserStorageRelationsPatchesDto {
    @JsonProperty("userRole")
    private UserStorageRole userRole;
    @JsonProperty("isIncludedInUserStatistics")
    private Boolean includedInUserStatistics;

    public UserStorageRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserStorageRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getIncludedInUserStatistics() {
        return includedInUserStatistics;
    }

    public void setIncludedInUserStatistics(Boolean includedInUserStatistics) {
        this.includedInUserStatistics = includedInUserStatistics;
    }
}
