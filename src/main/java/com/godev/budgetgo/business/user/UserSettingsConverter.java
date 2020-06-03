package com.godev.budgetgo.business.user;

import com.godev.budgetgo.api.rest.user.dto.UserSettingsInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.business.base.ConverterToDto;
import com.godev.budgetgo.business.base.Merger;
import com.godev.budgetgo.domain.user.User;

public interface UserSettingsConverter extends ConverterToDto<User, UserSettingsInfoDto>, Merger<User, UserSettingsPatchesDto> {
}
