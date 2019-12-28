package com.godev.budgetgo.request.impl;

import com.godev.budgetgo.converter.UsersConverter;
import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.request.UsersRequestService;
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
        return converter.convertFromEntity(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoDto getByLogin(String login) {
        User entity = dataService.getByLogin(login);
        return converter.convertFromEntity(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoDto getByEmail(String email) {
        User entity = dataService.getByEmail(email);
        return converter.convertFromEntity(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInfoDto> getAll() {
        return converter.convertFromEntities(dataService.getAll());
    }

    @Transactional
    @Override
    public UserInfoDto create(UserCreationDto creationDto) {
        User entity = converter.convertFromDto(creationDto);
        User savedEntity = dataService.add(entity);
        return converter.convertFromEntity(savedEntity);
    }
}
