package com.godev.budgetgo.controller.impl;

import com.godev.budgetgo.controller.StorageSettingsController;
import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.request.StorageSettingsRequestService;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StorageSettingsControllerImpl implements StorageSettingsController {

    private final StorageSettingsRequestService requestService;

    public StorageSettingsControllerImpl(StorageSettingsRequestService requestService) {
        this.requestService = requestService;
    }

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
