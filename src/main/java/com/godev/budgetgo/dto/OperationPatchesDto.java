package com.godev.budgetgo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.godev.budgetgo.json.LocalDateDeserializer;

import java.time.LocalDate;

public class OperationPatchesDto {
    private Long moneyDelta;
    private Long categoryId;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String description;

    public Long getMoneyDelta() {
        return moneyDelta;
    }

    public void setMoneyDelta(Long moneyDelta) {
        this.moneyDelta = moneyDelta;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
}
