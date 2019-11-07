package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.factory.CurrencyDtoFactory;
import com.godev.budgetgo.service.factory.UserSettingsDtoFactory;
import org.springframework.stereotype.Service;

@Service
class UserSettingsDtoFactoryImpl implements UserSettingsDtoFactory {

    private final CurrencyDtoFactory currencyDtoFactory;

    public UserSettingsDtoFactoryImpl(CurrencyDtoFactory currencyDtoFactory) {
        this.currencyDtoFactory = currencyDtoFactory;
    }

    @Override
    public UserSettingsInfoDto createFrom(User e) {
        UserSettingsInfoDto dto = new UserSettingsInfoDto();
        dto.setId(e.getId());
        dto.setLogin(e.getLogin());
        dto.setEmail(e.getEmail());
        dto.setEmailPublic(e.isEmailPublic());
        dto.setName(e.getName());
        dto.setSurname(e.getSurname());
        dto.setMainCurrencyInfoDto(currencyDtoFactory.createFrom(e.getMainCurrency()));
        return dto;
    }
}
