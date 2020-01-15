package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRole;

public class StorageRelationsInfoDto {

    @JsonProperty("user")
    private UserInfoDto userInfoDto;

    private UserStorageRole userRole;

    @JsonProperty("inviter")
    private UserInfoDto inviterInfoDto;

    public UserInfoDto getUserInfoDto() {
        return userInfoDto;
    }

    public void setUserInfoDto(UserInfoDto userInfoDto) {
        this.userInfoDto = userInfoDto;
    }

    public UserStorageRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserStorageRole userRole) {
        this.userRole = userRole;
    }

    public UserInfoDto getInviterInfoDto() {
        return inviterInfoDto;
    }

    public void setInviterInfoDto(UserInfoDto inviterInfoDto) {
        this.inviterInfoDto = inviterInfoDto;
    }
}
