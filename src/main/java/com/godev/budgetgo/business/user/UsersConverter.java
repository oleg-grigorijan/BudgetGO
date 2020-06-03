package com.godev.budgetgo.business.user;

import com.godev.budgetgo.api.rest.user.dto.UserCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserInfoDto;
import com.godev.budgetgo.business.base.ConverterToDto;
import com.godev.budgetgo.business.base.ConverterToEntity;
import com.godev.budgetgo.domain.user.User;

public interface UsersConverter extends ConverterToDto<User, UserInfoDto>, ConverterToEntity<UserCreationDto, User> {
}
