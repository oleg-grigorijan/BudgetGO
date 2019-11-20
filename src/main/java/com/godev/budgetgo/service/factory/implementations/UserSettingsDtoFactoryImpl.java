package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.converter.CurrenciesConverter;
import com.godev.budgetgo.service.factory.UserSettingsDtoFactory;
import org.springframework.stereotype.Service;

@Service
class UserSettingsDtoFactoryImpl implements UserSettingsDtoFactory {

    private final CurrenciesConverter currenciesConverter;

    public UserSettingsDtoFactoryImpl(CurrenciesConverter currenciesConverter) {
        this.currenciesConverter = currenciesConverter;
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
        dto.setMainCurrencyInfoDto(currenciesConverter.convertFromEntity(e.getMainCurrency()));
        return dto;
    }
}
