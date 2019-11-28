package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCategoryInfoDto {

    @JsonProperty("category")
    private CategoryInfoDto categoryInfoDto;

    public CategoryInfoDto getCategoryInfoDto() {
        return categoryInfoDto;
    }

    public void setCategoryInfoDto(CategoryInfoDto categoryInfoDto) {
        this.categoryInfoDto = categoryInfoDto;
    }
}
