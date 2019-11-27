package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.entity.User;

public interface UserSettingsConverter extends EntityConverter<User, UserSettingsInfoDto>, Merger<User, UserSettingsPatchesDto> {
}