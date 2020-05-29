package com.godev.budgetgo.api.rest.storage.impl;

import com.godev.budgetgo.api.rest.storage.StorageSettingsController;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.business.storage.StorageSettingsRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StorageSettingsControllerImpl implements StorageSettingsController {

    private final StorageSettingsRequestService requestService;

    @Override
    public StorageSettingsInfoDto getById(Long id) {
        return requestService.getByStorageId(id);
    }

    @Override
    public StorageSettingsInfoDto patchById(Long id, @Valid StorageSettingsPatchesDto patchesDto) {
        return requestService.patch(id, patchesDto);
    }

    @Override
    public void deleteById(Long id) {
        requestService.deleteByStorageId(id);
    }
}
