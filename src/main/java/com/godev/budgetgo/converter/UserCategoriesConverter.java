package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.UserCategoryCreationDto;
import com.godev.budgetgo.dto.UserCategoryInfoDto;
import com.godev.budgetgo.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.entity.UserCategory;

public interface UserCategoriesConverter extends ConverterToDto<UserCategory, UserCategoryInfoDto>,
        ConverterToEntity<UserCategoryCreationDto, UserCategory>, Merger<UserCategory, UserCategoryPatchesDto> {
}
