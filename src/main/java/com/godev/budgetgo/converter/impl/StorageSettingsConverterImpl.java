package com.godev.budgetgo.converter.impl;

import com.godev.budgetgo.converter.StorageSettingsConverter;
import com.godev.budgetgo.converter.UsersConverter;
import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;
import org.springframework.stereotype.Service;

@Service
class StorageSettingsConverterImpl implements StorageSettingsConverter {

    private final UsersConverter usersConverter;

    public StorageSettingsConverterImpl(UsersConverter usersConverter) {
        this.usersConverter = usersConverter;
    }

    @Override
    public StorageSettingsInfoDto convertFromEntity(StorageRelations e) {
        StorageSettingsInfoDto dto = new StorageSettingsInfoDto();
        dto.setUserRole(e.getUserRole());
        dto.setIncludedInUserStatistics(e.isIncludedInUserStatistics());
        dto.setInvitation(e.isInvitation());
        dto.setInviterInfoDto(usersConverter.convertFromEntity(e.getInviter()));
        return dto;
    }

    @Override
    public StorageRelations merge(StorageRelations eOld, StorageSettingsPatchesDto dto) {
        StorageRelations e = eOld.clone();
        dto.getIncludedInUserStatistics().ifPresent(e::setIncludedInUserStatistics);
        dto.getInvitation().ifPresent(e::setInvitation);
        return e;
    }
}
