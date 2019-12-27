package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.request.UserSettingsRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/me")
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
