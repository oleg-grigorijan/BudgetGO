package com.godev.budgetgo.dto;

import javax.validation.constraints.NotNull;

public class CollectionCreationDto {

    @NotNull
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
