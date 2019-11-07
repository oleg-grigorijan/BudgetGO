package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.service.factory.StorageRelationsDtoFactory;
import com.godev.budgetgo.service.factory.UserDtoFactory;
import org.springframework.stereotype.Service;

@Service
class StorageRelationsDtoFactoryImpl implements StorageRelationsDtoFactory {

    private final UserDtoFactory userDtoFactory;

    public StorageRelationsDtoFactoryImpl(UserDtoFactory userDtoFactory) {
        this.userDtoFactory = userDtoFactory;
    }

    @Override
    public StorageRelationsInfoDto createFrom(StorageRelations e) {
        StorageRelationsInfoDto dto = new StorageRelationsInfoDto();
        dto.setUserInfoDto(userDtoFactory.createFrom(e.getUser()));
        dto.setUserRole(e.getUserRole());
        dto.setInviterInfoDto(userDtoFactory.createFrom(e.getInviter()));
        return dto;
    }
}
