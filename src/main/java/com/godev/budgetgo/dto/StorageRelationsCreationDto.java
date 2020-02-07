package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.UserStorageRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class StorageRelationsCreationDto {

    @NotNull
    private Long userId;

    @NotNull
    private UserStorageRole userRole;
}
