package com.godev.budgetgo.api.rest.user.impl;

import com.godev.budgetgo.api.rest.user.UserSettingsController;
import com.godev.budgetgo.api.rest.user.dto.UserSettingsInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.business.user.UserSettingsRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserSettingsControllerImpl implements UserSettingsController {

    private final UserSettingsRequestService requestService;

    @Override
    public UserSettingsInfoDto get() {
        return requestService.get();
    }

    @Override
    public UserSettingsInfoDto patch(@Valid UserSettingsPatchesDto patchesDto) {
        return requestService.patch(patchesDto);
    }
}
