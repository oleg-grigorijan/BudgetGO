package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.service.merger.CategoriesMerger;

public class CategoriesMergerImpl implements CategoriesMerger {
    @Override
    public void merge(CategoryPatchesDto dto, Category e) {
        e.setName(dto.getName());
    }
}
