package com.godev.budgetgo.service.converter;

import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.entity.User;

public interface UserSettingsConverter extends EntityConverter<User, UserSettingsInfoDto>, Merger<User, UserSettingsPatchesDto> {
}
