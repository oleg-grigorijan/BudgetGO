package com.godev.budgetgo.service;


import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.dto.UserPatchesDto;

public interface UsersService {
    UserInfoDto findById(Long id);

    UserInfoDto findByEmail(String email);

    UserInfoDto findByLogin(String login);

    UserInfoDto create(UserCreationDto creationDto);

    UserInfoDto patch(Long id, UserPatchesDto patchesDto);

    void deleteById(Long id);
}
