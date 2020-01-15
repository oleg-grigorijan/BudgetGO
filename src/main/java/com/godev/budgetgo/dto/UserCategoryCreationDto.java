package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UserCategoryCreationDto {

    @NotNull
    private Long categoryId;

    @JsonProperty("isUsedForIncomes")
    private boolean usedForIncomes = true;

    @JsonProperty("isUsedForOutcomes")
    private boolean usedForOutcomes = true;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public boolean getUsedForIncomes() {
        return usedForIncomes;
    }

    public void setUsedForIncomes(boolean usedForIncomes) {
        this.usedForIncomes = usedForIncomes;
    }

    public boolean getUsedForOutcomes() {
        return usedForOutcomes;
    }

    public void setUsedForOutcomes(boolean usedForOutcomes) {
        this.usedForOutcomes = usedForOutcomes;
    }
}
