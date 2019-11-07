package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.service.request.StorageSettingsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/storages/{id}/settings")
public class StorageSettingsController {

    private final StorageSettingsRequestService requestService;

    public StorageSettingsController(StorageSettingsRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.OK)
    public StorageSettingsInfoDto get(@PathVariable Long id) {
        return requestService.getByStorageId(id);
    }

    @PatchMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.OK)
    public StorageSettingsInfoDto patch(
            @PathVariable Long id,
            @RequestBody StorageSettingsPatchesDto patchesDto
    ) {
        return requestService.patch(id, patchesDto);
    }
}
