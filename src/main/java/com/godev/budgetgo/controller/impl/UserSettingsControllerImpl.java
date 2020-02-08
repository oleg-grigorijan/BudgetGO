package com.godev.budgetgo.controller.impl;

import com.godev.budgetgo.controller.UserSettingsController;
import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.request.UserSettingsRequestService;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserSettingsControllerImpl implements UserSettingsController {

    private final UserSettingsRequestService requestService;

    public UserSettingsControllerImpl(UserSettingsRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public UserSettingsInfoDto get() {
        return requestService.get();
    }

    @Override
    public UserSettingsInfoDto patch(@Valid UserSettingsPatchesDto patchesDto) {
        return requestService.patch(patchesDto);
    }
}
