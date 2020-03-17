package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.business.base.ConverterToDto;
import com.godev.budgetgo.business.base.Merger;
import com.godev.budgetgo.domain.storage.StorageRelations;

public interface StorageSettingsConverter extends ConverterToDto<StorageRelations, StorageSettingsInfoDto>,
        Merger<StorageRelations, StorageSettingsPatchesDto> {
}
