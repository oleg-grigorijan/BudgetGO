package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.request.StorageSettingsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public StorageSettingsInfoDto getByStorageId(@PathVariable Long id) {
        return requestService.getByStorageId(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public StorageSettingsInfoDto patch(@PathVariable Long id, @RequestBody @Valid StorageSettingsPatchesDto patchesDto) {
        return requestService.patch(id, patchesDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByStorageId(@PathVariable Long id) {
        requestService.deleteByStorageId(id);
    }
}
