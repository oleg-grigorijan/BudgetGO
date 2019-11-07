package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRole;

public class StorageSettingsInfoDto {
    private UserStorageRole userRole;
    @JsonProperty("isIncludedInUserStatistics")
    private boolean includedInUserStatistics;
    @JsonProperty("inviter")
    private UserInfoDto inviterInfoDto;
    @JsonProperty("isInvitation")
    private boolean invitation;

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

    public UserInfoDto getInviterInfoDto() {
        return inviterInfoDto;
    }

    public void setInviterInfoDto(UserInfoDto inviterInfoDto) {
        this.inviterInfoDto = inviterInfoDto;
    }

    public boolean getInvitation() {
        return invitation;
    }

    public void setInvitation(boolean invitation) {
        this.invitation = invitation;
    }
}
