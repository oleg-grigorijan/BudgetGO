package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.validation.constraint.NullOrNotBlank;

import javax.validation.constraints.Size;

public class CategoryPatchesDto {

    @NullOrNotBlank
    @Size(max = Category.NAME_MAX_LENGTH)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
