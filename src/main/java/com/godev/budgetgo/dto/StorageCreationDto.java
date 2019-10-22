package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Storage;

public class StorageCreationDto {
    private String name;
    private String description = "";
    private long initialBalance;
    private Long currencyId;

    public Storage getStorage() {
        Storage storage = new Storage();
        storage.setName(name);
        storage.setDescription(description);
        storage.setBalance(initialBalance);
        return storage;
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
