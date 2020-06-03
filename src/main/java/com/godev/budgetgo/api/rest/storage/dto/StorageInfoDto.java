package com.godev.budgetgo.api.rest.storage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyInfoDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Storage info model")
@Data
@NoArgsConstructor
public class StorageInfoDto {

    private Long id;

    private String name;

    private String description;

    private long balance;

    @JsonProperty("currency")
    private CurrencyInfoDto currencyInfoDto;

    private long initialBalance;

    @JsonProperty("settings")
    private StorageSettingsInfoDto storageSettingsInfoDto;
}
