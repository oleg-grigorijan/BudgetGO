package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StorageInfoDto {
    private Long id;
    private String name;
    private String description;
    private long balance;
    @JsonProperty("currency")
    private CurrencyInfoDto currencyInfoDto;

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
