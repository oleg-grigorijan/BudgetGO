package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;

public interface UserSettingsRequestService {
    UserSettingsInfoDto get();

    UserSettingsInfoDto patch(UserSettingsPatchesDto patchesDto);
}
