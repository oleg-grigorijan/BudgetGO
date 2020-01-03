package com.godev.budgetgo.converter.impl;

import com.godev.budgetgo.converter.CurrenciesConverter;
import com.godev.budgetgo.converter.UserSettingsConverter;
import com.godev.budgetgo.data.CurrenciesDataService;
import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.NotFoundException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSettingsConverterImpl implements UserSettingsConverter {

    private final CurrenciesDataService currenciesDataService;
    private final PasswordEncoder passwordEncoder;
    private final CurrenciesConverter currenciesConverter;

    public UserSettingsConverterImpl(
            CurrenciesDataService currenciesDataService,
            PasswordEncoder passwordEncoder,
            CurrenciesConverter currenciesConverter
    ) {
        this.currenciesDataService = currenciesDataService;
        this.passwordEncoder = passwordEncoder;
        this.currenciesConverter = currenciesConverter;
    }

    @Override
    public UserSettingsInfoDto convertToDto(User e) {
        UserSettingsInfoDto dto = new UserSettingsInfoDto();
        dto.setId(e.getId());
        dto.setLogin(e.getLogin());
        dto.setEmail(e.getEmail());
        dto.setEmailPublic(e.isEmailPublic());
        dto.setName(e.getName());
        dto.setSurname(e.getSurname());
        dto.setMainCurrencyInfoDto(currenciesConverter.convertToDto(e.getMainCurrency()));
        return dto;
    }

    @Override
    public User merge(User eOld, UserSettingsPatchesDto dto) {
        try {
            User e = eOld.clone();
            dto.getLogin().ifPresent(e::setLogin);
            dto.getEmail().ifPresent(e::setEmail);
            dto.getName().ifPresent(e::setName);
            dto.getSurname().ifPresent(e::setSurname);
            dto.getPassword()
                    .ifPresent(password -> e.setPasswordHash(passwordEncoder.encode(password)));
            dto.getEmailPublic().ifPresent(e::setEmailPublic);
            dto.getMainCurrencyId()
                    .ifPresent(currencyId -> e.setMainCurrency(currenciesDataService.getById(currencyId)));
            return e;

        } catch (NotFoundException ex) {
            throw new UnprocessableEntityException(ex);
        }
    }
}
