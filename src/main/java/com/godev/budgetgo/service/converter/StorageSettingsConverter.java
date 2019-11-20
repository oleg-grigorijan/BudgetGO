package com.godev.budgetgo.service.converter;

import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;

public interface StorageSettingsConverter extends EntityConverter<StorageRelations, StorageSettingsInfoDto>,
        Merger<StorageRelations, StorageSettingsPatchesDto> {
}
