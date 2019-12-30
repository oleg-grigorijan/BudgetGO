package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;

public interface CategoriesConverter extends ConverterToDto<Category, CategoryInfoDto>, ConverterToEntity<CategoryCreationDto, Category>,
        Merger<Category, CategoryPatchesDto> {
}
