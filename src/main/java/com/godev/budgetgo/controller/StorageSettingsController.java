package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.request.StorageSettingsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/storages/{id}/settings")
public class StorageSettingsController {

    private final StorageSettingsRequestService requestService;

    public StorageSettingsController(StorageSettingsRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StorageSettingsInfoDto get(@PathVariable Long id) {
        return requestService.getByStorageId(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public StorageSettingsInfoDto patch(@PathVariable Long id, @RequestBody @Valid StorageSettingsPatchesDto patchesDto) {
        return requestService.patch(id, patchesDto);
    }
}
