package com.godev.budgetgo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.godev.budgetgo.json.LocalDateSerializer;

import java.time.LocalDate;

public class OperationInfoDto {
    private Long id;
    private StorageInfoDto storage;
    private long moneyDelta;
    private CategoryInfoDto category;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private String description;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateCreated;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StorageInfoDto getStorage() {
        return storage;
    }

    public void setStorage(StorageInfoDto storage) {
        this.storage = storage;
    }

    public long getMoneyDelta() {
        return moneyDelta;
    }

    public void setMoneyDelta(long moneyDelta) {
        this.moneyDelta = moneyDelta;
    }

    public CategoryInfoDto getCategory() {
        return category;
    }

    public void setCategory(CategoryInfoDto category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }
}
