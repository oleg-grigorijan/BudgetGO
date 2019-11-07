package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.factory.UserSettingsDtoFactory;
import org.springframework.stereotype.Service;

@Service
class UserSettingsDtoFactoryImpl implements UserSettingsDtoFactory {
    @Override
    public UserSettingsInfoDto createFrom(User e) {
        UserSettingsInfoDto dto = new UserSettingsInfoDto();
        dto.setId(e.getId());
        dto.setLogin(e.getLogin());
        dto.setEmail(e.getEmail());
        dto.setEmailPublic(e.isEmailPublic());
        dto.setName(e.getName());
        dto.setSurname(e.getSurname());
        return dto;
    }
}