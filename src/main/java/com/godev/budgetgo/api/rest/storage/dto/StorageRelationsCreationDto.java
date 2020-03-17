package com.godev.budgetgo.api.rest.storage.dto;

import com.godev.budgetgo.domain.storage.UserStorageRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@ApiModel("Storage user invitation model")
@Data
@NoArgsConstructor
public class StorageRelationsCreationDto {

    @NotNull
    private Long userId;

    @NotNull
    private UserStorageRole userRole;
}
