package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Storage;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StorageCreationDto {

    @NotBlank
    @Size(max = Storage.NAME_MAX_LENGTH)
    private String name;

    @Size(max = Storage.DESCRIPTION_MAX_LENGTH)
    private String description = "";

    private long initialBalance;

    @NotNull
    private Long currencyId;

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

    public long getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(long initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }
}
