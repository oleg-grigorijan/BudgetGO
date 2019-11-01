package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.entity.Category;

public interface CategoriesAuthorizationService
        extends AuthorizationService<Category, CategoryCreationDto, CategoryPatchesDto> {
}
