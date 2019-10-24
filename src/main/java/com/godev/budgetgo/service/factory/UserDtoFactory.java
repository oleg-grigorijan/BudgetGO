package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.entity.User;

public interface UserDtoFactory extends ConverterFactory<User, UserInfoDto> {
}