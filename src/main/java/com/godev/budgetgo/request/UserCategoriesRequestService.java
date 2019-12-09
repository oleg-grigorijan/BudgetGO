package com.godev.budgetgo.request;

import com.godev.budgetgo.dto.UserCategoryCreationDto;
import com.godev.budgetgo.dto.UserCategoryInfoDto;
import com.godev.budgetgo.dto.UserCategoryPatchesDto;

import java.util.List;

public interface UserCategoriesRequestService {
    List<UserCategoryInfoDto> getAll();

    UserCategoryInfoDto getByCategoryId(Long categoryId);

    UserCategoryInfoDto create(UserCategoryCreationDto creationDto);

    UserCategoryInfoDto patchByCategoryId(Long categoryId, UserCategoryPatchesDto patchesDto);

    void deleteByCategoryId(Long categoryId);
}
