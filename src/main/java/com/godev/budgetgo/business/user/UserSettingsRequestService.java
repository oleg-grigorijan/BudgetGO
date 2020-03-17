package com.godev.budgetgo.business.user;

import com.godev.budgetgo.api.rest.user.dto.UserSettingsInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserSettingsPatchesDto;

public interface UserSettingsRequestService {

    UserSettingsInfoDto get();

    UserSettingsInfoDto patch(UserSettingsPatchesDto patchesDto);
}
