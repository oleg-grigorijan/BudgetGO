package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.service.converter.UsersConverter;
import com.godev.budgetgo.service.factory.StorageRelationsDtoFactory;
import org.springframework.stereotype.Service;

@Service
class StorageRelationsDtoFactoryImpl implements StorageRelationsDtoFactory {

    private final UsersConverter usersConverter;

    public StorageRelationsDtoFactoryImpl(UsersConverter usersConverter) {
        this.usersConverter = usersConverter;
    }

    @Override
    public StorageRelationsInfoDto createFrom(StorageRelations e) {
        StorageRelationsInfoDto dto = new StorageRelationsInfoDto();
        dto.setUserInfoDto(usersConverter.convertFromEntity(e.getUser()));
        dto.setUserRole(e.getUserRole());
        dto.setInviterInfoDto(usersConverter.convertFromEntity(e.getInviter()));
        return dto;
    }
}
