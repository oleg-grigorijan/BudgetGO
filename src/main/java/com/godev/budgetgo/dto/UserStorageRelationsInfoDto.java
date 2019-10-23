package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.entity.UserStorageRole;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStorageRelationsInfoDto {
    @JsonProperty("user")
    private UserInfoDto userInfoDto;
    @JsonProperty("userRole")
    private UserStorageRole userRole;
    @JsonProperty("isIncludedInUserStatistics")
    private Boolean includedInUserStatistics;

    public static UserStorageRelationsInfoDto from(UserStorageRelations relations) {
        UserStorageRelationsInfoDto dto = publicInfoFrom(relations);
        dto.includedInUserStatistics = relations.isIncludedInUserStatistics();
        return dto;
    }

    public static UserStorageRelationsInfoDto publicInfoFrom(UserStorageRelations relations) {
        UserStorageRelationsInfoDto dto = new UserStorageRelationsInfoDto();
        dto.userInfoDto = UserInfoDto.publicInfoFrom(relations.getUser());
        dto.userRole = relations.getUserRole();
        return dto;
    }

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

    public Boolean isIncludedInUserStatistics() {
        return includedInUserStatistics;
    }

    public void setIncludedInUserStatistics(Boolean includedInUserStatistics) {
        this.includedInUserStatistics = includedInUserStatistics;
    }
}
