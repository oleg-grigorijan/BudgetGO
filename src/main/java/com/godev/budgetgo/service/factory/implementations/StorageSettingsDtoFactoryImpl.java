package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.service.converter.UsersConverter;
import com.godev.budgetgo.service.factory.StorageSettingsDtoFactory;
import org.springframework.stereotype.Service;

@Service
class StorageSettingsDtoFactoryImpl implements StorageSettingsDtoFactory {

    private final UsersConverter usersConverter;

    public StorageSettingsDtoFactoryImpl(UsersConverter usersConverter) {
        this.usersConverter = usersConverter;
    }

    @Override
    public StorageSettingsInfoDto createFrom(StorageRelations e) {
        StorageSettingsInfoDto dto = new StorageSettingsInfoDto();
        dto.setUserRole(e.getUserRole());
        dto.setIncludedInUserStatistics(e.isIncludedInUserStatistics());
        dto.setInvitation(e.isInvitation());
        dto.setInviterInfoDto(usersConverter.convertFromEntity(e.getInviter()));
        return dto;
    }
}
