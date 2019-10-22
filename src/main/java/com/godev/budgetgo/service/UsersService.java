package com.godev.budgetgo.service;


import com.godev.budgetgo.dto.UserDto;
import com.godev.budgetgo.dto.UserPublicInfoDto;

public interface UsersService {
    UserPublicInfoDto findById(Long id);

    UserPublicInfoDto findByEmail(String email);

    UserPublicInfoDto findByLogin(String login);

    UserDto create(UserDto entity);

    UserDto update(UserDto entity);

    void deleteById(Long id);
}
