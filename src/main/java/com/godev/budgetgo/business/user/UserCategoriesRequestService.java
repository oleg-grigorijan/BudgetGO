package com.godev.budgetgo.business.user;

import com.godev.budgetgo.api.rest.user.dto.UserCategoryCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryPatchesDto;

import java.util.List;

public interface UserCategoriesRequestService {

    List<UserCategoryInfoDto> getAll();

    UserCategoryInfoDto getByCategoryId(Long categoryId);

    UserCategoryInfoDto create(UserCategoryCreationDto creationDto);

    UserCategoryInfoDto patchByCategoryId(Long categoryId, UserCategoryPatchesDto patchesDto);

    void deleteByCategoryId(Long categoryId);
}
