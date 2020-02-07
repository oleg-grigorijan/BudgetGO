package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StorageRelationsInfoDto {

    @JsonProperty("user")
    private UserInfoDto userInfoDto;

    private UserStorageRole userRole;

    @JsonProperty("inviter")
    private UserInfoDto inviterInfoDto;
}
