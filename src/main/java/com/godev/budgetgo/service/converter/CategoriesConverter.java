package com.godev.budgetgo.service.converter;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;

public interface CategoriesConverter extends EntityConverter<Category, CategoryInfoDto>, DtoConverter<CategoryCreationDto, Category>,
        Merger<Category, CategoryPatchesDto> {
}
