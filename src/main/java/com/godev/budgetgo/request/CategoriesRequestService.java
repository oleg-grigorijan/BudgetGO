package com.godev.budgetgo.request;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;

import java.util.List;

public interface CategoriesRequestService {
    CategoryInfoDto getById(Long id);

    List<CategoryInfoDto> getAll();

    CategoryInfoDto create(CategoryCreationDto creationDto);

    CategoryInfoDto patch(Long id, CategoryPatchesDto patchesDto);
}
