package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.service.merger.CategoriesMerger;
import org.springframework.stereotype.Service;

@Service
public class CategoriesMergerImpl implements CategoriesMerger {
    @Override
    public Category merge(CategoryPatchesDto dto, Category eOld) {
        Category e = eOld.clone();
        e.setName(dto.getName());
        return e;
    }
}
