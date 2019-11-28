package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.UserCategoryCreationDto;
import com.godev.budgetgo.dto.UserCategoryInfoDto;
import com.godev.budgetgo.entity.UserCategory;

public interface UserCategoriesConverter extends EntityConverter<UserCategory, UserCategoryInfoDto>, DtoConverter<UserCategoryCreationDto, UserCategory> {
}
