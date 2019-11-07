package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRole;

public class StorageSettingsInfoDto {
    private UserStorageRole userRole;
    @JsonProperty("isIncludedInUserStatistics")
    private boolean includedInUserStatistics;

    public UserStorageRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserStorageRole userRole) {
        this.userRole = userRole;
    }

    public boolean getIncludedInUserStatistics() {
        return includedInUserStatistics;
    }

    public void setIncludedInUserStatistics(boolean includedInUserStatistics) {
        this.includedInUserStatistics = includedInUserStatistics;
    }
}
