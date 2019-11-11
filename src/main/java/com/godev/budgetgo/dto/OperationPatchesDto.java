package com.godev.budgetgo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.json.LocalDateDeserializer;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Optional;

public class OperationPatchesDto {

    private Long moneyDelta;

    private Long categoryId;

    @PastOrPresent
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @Size(max = Operation.DESCRIPTION_MAX_LENGTH)
    private String description;

    public Optional<Long> getMoneyDelta() {
        return Optional.ofNullable(moneyDelta);
    }

    public void setMoneyDelta(Long moneyDelta) {
        this.moneyDelta = moneyDelta;
    }

    public Optional<Long> getCategoryId() {
        return Optional.ofNullable(categoryId);
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Optional<LocalDate> getDate() {
        return Optional.ofNullable(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
