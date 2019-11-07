package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.service.factory.StorageSettingsDtoFactory;
import com.godev.budgetgo.service.factory.UserDtoFactory;
import org.springframework.stereotype.Service;

@Service
class StorageSettingsDtoFactoryImpl implements StorageSettingsDtoFactory {

    private final UserDtoFactory userDtoFactory;

    public StorageSettingsDtoFactoryImpl(UserDtoFactory userDtoFactory) {
        this.userDtoFactory = userDtoFactory;
    }

    @Override
    public StorageSettingsInfoDto createFrom(StorageRelations e) {
        StorageSettingsInfoDto dto = new StorageSettingsInfoDto();
        dto.setUserRole(e.getUserRole());
        dto.setIncludedInUserStatistics(e.isIncludedInUserStatistics());
        dto.setInvitation(e.isInvitation());
        dto.setInviterInfoDto(userDtoFactory.createFrom(e.getInviter()));
        return dto;
    }
}
