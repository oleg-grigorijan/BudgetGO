package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.UserStorageRelationsInfoDto;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.service.factory.UserDtoFactory;
import com.godev.budgetgo.service.factory.UserStorageRelationsDtoFactory;
import org.springframework.stereotype.Service;

@Service
class UserStorageRelationsDtoFactoryImpl implements UserStorageRelationsDtoFactory {

    private final UserDtoFactory userDtoFactory;

    public UserStorageRelationsDtoFactoryImpl(UserDtoFactory userDtoFactory) {
        this.userDtoFactory = userDtoFactory;
    }

    @Override
    public UserStorageRelationsInfoDto createFrom(UserStorageRelations e) {
        UserStorageRelationsInfoDto dto = new UserStorageRelationsInfoDto();
        dto.setUserInfoDto(userDtoFactory.createFrom(e.getUser()));
        dto.setUserRole(e.getUserRole());
        dto.setIncludedInUserStatistics(e.isIncludedInUserStatistics());
        return dto;
    }
}
