package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.entity.User;

public interface UsersConverter extends ConverterToDto<User, UserInfoDto>, ConverterToEntity<UserCreationDto, User> {
}
