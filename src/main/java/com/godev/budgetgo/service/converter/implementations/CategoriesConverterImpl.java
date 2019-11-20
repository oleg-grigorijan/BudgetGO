package com.godev.budgetgo.service.converter.implementations;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.service.converter.CategoriesConverter;
import org.springframework.stereotype.Service;

@Service
class CategoriesConverterImpl implements CategoriesConverter {
    @Override
    public Category convertFromDto(CategoryCreationDto dto) {
        Category e = new Category();
        e.setName(dto.getName());
        return e;
    }

    @Override
    public CategoryInfoDto convertFromEntity(Category e) {
        CategoryInfoDto dto = new CategoryInfoDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        return dto;
    }

    @Override
    public Category merge(Category eOld, CategoryPatchesDto dto) {
        Category e = eOld.clone();
        dto.getName().ifPresent(e::setName);
        return e;
    }
}
