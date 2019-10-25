package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;

public interface CategoriesRequestService
        extends RequestService<Long, CategoryInfoDto, CategoryCreationDto, CategoryPatchesDto> {
}
