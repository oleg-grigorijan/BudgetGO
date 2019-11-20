package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.request.UserSettingsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/settings")
public class UserSettingsController {

    private final UserSettingsRequestService requestService;

    public UserSettingsController(UserSettingsRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserSettingsInfoDto get() {
        return requestService.get();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public UserSettingsInfoDto patch(@RequestBody @Valid UserSettingsPatchesDto patchesDto) {
        return requestService.patch(patchesDto);
    }
}
