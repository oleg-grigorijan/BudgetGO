package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRole;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStorageRelationsInfoDto {
    @JsonProperty("user")
    private UserInfoDto userInfoDto;
    @JsonProperty("userRole")
    private UserStorageRole userStorageRole;
    @JsonProperty("isIncludedInUserStatistics")
    private Boolean includedInUserStatistics;

    public UserInfoDto getUserInfoDto() {
        return userInfoDto;
    }

    public void setUserInfoDto(UserInfoDto userInfoDto) {
        this.userInfoDto = userInfoDto;
    }

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
