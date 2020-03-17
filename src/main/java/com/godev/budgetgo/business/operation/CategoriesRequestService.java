package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.api.rest.operation.dto.CategoryCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryPatchesDto;

import java.util.List;

public interface CategoriesRequestService {

    CategoryInfoDto getById(Long id);

    CategoryInfoDto getByName(String name);

    List<CategoryInfoDto> getAll();

    CategoryInfoDto create(CategoryCreationDto creationDto);

    CategoryInfoDto patch(Long id, CategoryPatchesDto patchesDto);
}
