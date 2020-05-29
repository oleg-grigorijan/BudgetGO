package com.godev.budgetgo.business.user;


import com.godev.budgetgo.api.rest.user.dto.UserCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserInfoDto;

import java.util.List;

public interface UsersRequestService {

    UserInfoDto getById(Long id);

    UserInfoDto getByLogin(String login);

    UserInfoDto getByEmail(String email);

    List<UserInfoDto> getAll();

    UserInfoDto create(UserCreationDto creationDto);
}
