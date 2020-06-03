package com.godev.budgetgo.business.user;

import com.godev.budgetgo.api.rest.user.dto.UserCategoryCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.business.base.ConverterToDto;
import com.godev.budgetgo.business.base.ConverterToEntity;
import com.godev.budgetgo.business.base.Merger;
import com.godev.budgetgo.domain.user.UserCategory;

public interface UserCategoriesConverter extends ConverterToDto<UserCategory, UserCategoryInfoDto>,
        ConverterToEntity<UserCategoryCreationDto, UserCategory>, Merger<UserCategory, UserCategoryPatchesDto> {
}
