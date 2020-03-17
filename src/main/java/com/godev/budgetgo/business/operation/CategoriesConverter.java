package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.api.rest.operation.dto.CategoryCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryPatchesDto;
import com.godev.budgetgo.business.base.ConverterToDto;
import com.godev.budgetgo.business.base.ConverterToEntity;
import com.godev.budgetgo.business.base.Merger;
import com.godev.budgetgo.domain.operation.Category;

public interface CategoriesConverter extends ConverterToDto<Category, CategoryInfoDto>, ConverterToEntity<CategoryCreationDto, Category>,
        Merger<Category, CategoryPatchesDto> {
}
