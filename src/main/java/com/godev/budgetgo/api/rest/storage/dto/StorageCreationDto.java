package com.godev.budgetgo.api.rest.storage.dto;

import com.godev.budgetgo.domain.storage.Storage;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel("Storage creation model")
@Data
@NoArgsConstructor
public class StorageCreationDto {

    @NotBlank
    @Size(max = Storage.NAME_MAX_LENGTH)
    private String name;

    @Size(max = Storage.DESCRIPTION_MAX_LENGTH)
    private String description = "";

    private long initialBalance;

    @NotNull
    private Long currencyId;
}
