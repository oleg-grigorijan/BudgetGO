package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

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
