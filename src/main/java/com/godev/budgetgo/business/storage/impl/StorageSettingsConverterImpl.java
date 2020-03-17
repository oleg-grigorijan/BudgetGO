package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.business.storage.StorageSettingsConverter;
import com.godev.budgetgo.business.user.UsersConverter;
import com.godev.budgetgo.domain.storage.StorageRelations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageSettingsConverterImpl implements StorageSettingsConverter {

    private final UsersConverter usersConverter;

    @Override
    public StorageSettingsInfoDto convertToDto(StorageRelations e) {
        StorageSettingsInfoDto dto = new StorageSettingsInfoDto();
        dto.setUserRole(e.getUserRole());
        dto.setIncludedInUserStatistics(e.isIncludedInUserStatistics());
        dto.setInvitation(e.isInvitation());
        dto.setInviterInfoDto(usersConverter.convertToDto(e.getInviter()));
        return dto;
    }

    @Override
    public StorageRelations merge(StorageRelations eOld, StorageSettingsPatchesDto dto) {
        StorageRelations e = eOld.cloneShallow();
        dto.getIncludedInUserStatistics().ifPresent(e::setIncludedInUserStatistics);
        dto.getInvitation().ifPresent(e::setInvitation);
        return e;
    }
}
