package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCategoryInfoDto {

    @JsonProperty("category")
    private CategoryInfoDto categoryInfoDto;

    @JsonProperty("isUsedForIncomes")
    private boolean usedForIncomes;

    @JsonProperty("isUsedForOutcomes")
    private boolean usedForOutcomes;

    public CategoryInfoDto getCategoryInfoDto() {
        return categoryInfoDto;
    }

    public void setCategoryInfoDto(CategoryInfoDto categoryInfoDto) {
        this.categoryInfoDto = categoryInfoDto;
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
