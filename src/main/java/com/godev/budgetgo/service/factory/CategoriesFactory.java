package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.entity.Category;

public interface CategoriesFactory extends ConverterFactory<CategoryCreationDto, Category> {
}
