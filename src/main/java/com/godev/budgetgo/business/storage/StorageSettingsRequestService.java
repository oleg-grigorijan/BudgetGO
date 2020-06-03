package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsPatchesDto;

public interface StorageSettingsRequestService {

    StorageSettingsInfoDto getByStorageId(Long storageId);

    StorageSettingsInfoDto patch(Long storageId, StorageSettingsPatchesDto patchesDto);

    void deleteByStorageId(Long storageId);
}
