package com.godev.budgetgo.business.user.impl;

import com.godev.budgetgo.api.rest.user.dto.UserCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserInfoDto;
import com.godev.budgetgo.business.user.UsersConverter;
import com.godev.budgetgo.business.user.UsersDataService;
import com.godev.budgetgo.business.user.UsersRequestService;
import com.godev.budgetgo.domain.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersRequestServiceImpl implements UsersRequestService {

    private final UsersDataService dataService;

    private final UsersConverter converter;

    public UsersRequestServiceImpl(UsersDataService dataService, UsersConverter converter) {
        this.dataService = dataService;
        this.converter = converter;
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoDto getById(Long id) {
        User entity = dataService.getById(id);
        return converter.convertToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoDto getByLogin(String login) {
        User entity = dataService.getByLogin(login);
        return converter.convertToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoDto getByEmail(String email) {
        User entity = dataService.getByEmail(email);
        return converter.convertToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInfoDto> getAll() {
        return converter.convertToDtos(dataService.getAll());
    }

    @Transactional
    @Override
    public UserInfoDto create(UserCreationDto creationDto) {
        User entity = converter.convertToEntity(creationDto);
        User savedEntity = dataService.add(entity);
        return converter.convertToDto(savedEntity);
    }
}
