package com.godev.budgetgo.request;

import com.godev.budgetgo.dto.UserCategoryCreationDto;
import com.godev.budgetgo.dto.UserCategoryInfoDto;

import java.util.List;

public interface UserCategoriesRequestService {
    List<UserCategoryInfoDto> getAll();

    UserCategoryInfoDto getByCategoryId(Long categoryId);

    UserCategoryInfoDto create(UserCategoryCreationDto creationDto);

    void deleteByCategoryId(Long categoryId);
}
