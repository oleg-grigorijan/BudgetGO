package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.service.merger.CategoriesMerger;
import org.springframework.stereotype.Service;

@Service
class CategoriesMergerImpl implements CategoriesMerger {
    @Override
    public Category merge(CategoryPatchesDto dto, Category eOld) {
        Category e = eOld.clone();
        dto.getName().ifPresent(e::setName);
        return e;
    }
}
