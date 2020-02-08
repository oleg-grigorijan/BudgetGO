package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.UserStorageRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Storage settings info model")
@Data
@NoArgsConstructor
public class StorageSettingsInfoDto {

    private UserStorageRole userRole;

    @JsonProperty("isIncludedInUserStatistics")
    private boolean includedInUserStatistics;

    @JsonProperty("inviter")
    private UserInfoDto inviterInfoDto;

    @JsonProperty("isInvitation")
    private boolean invitation;
}
