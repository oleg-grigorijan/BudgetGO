package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;

public interface StorageSettingsConverter extends ConverterToDto<StorageRelations, StorageSettingsInfoDto>,
        Merger<StorageRelations, StorageSettingsPatchesDto> {
}
