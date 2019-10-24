package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.entity.User;

public interface UsersFactory extends ConverterFactory<UserCreationDto, User> {
}
