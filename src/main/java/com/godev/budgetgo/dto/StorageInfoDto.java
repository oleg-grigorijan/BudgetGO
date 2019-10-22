package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.Storage;

public class StorageInfoDto {
    private Long id;
    private String name;
    private String description;
    private long balance;
    @JsonProperty("currency")
    private CurrencyInfoDto currencyInfoDto;

    public static StorageInfoDto from(Storage storage) {
        StorageInfoDto dto = new StorageInfoDto();
        dto.id = storage.getId();
        dto.name = storage.getName();
        dto.description = storage.getDescription();
        dto.balance = storage.getBalance();
        dto.currencyInfoDto = CurrencyInfoDto.from(storage.getCurrency());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public CurrencyInfoDto getCurrencyInfoDto() {
        return currencyInfoDto;
    }

    public void setCurrencyInfoDto(CurrencyInfoDto currencyInfoDto) {
        this.currencyInfoDto = currencyInfoDto;
    }
}
