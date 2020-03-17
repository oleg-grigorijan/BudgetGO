package com.godev.budgetgo.business.user.impl;

import com.godev.budgetgo.api.rest.user.dto.UserSettingsInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.business.currency.CurrenciesConverter;
import com.godev.budgetgo.business.currency.CurrenciesDataService;
import com.godev.budgetgo.business.user.UserSettingsConverter;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.error.exception.NotFoundException;
import com.godev.budgetgo.infra.error.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingsConverterImpl implements UserSettingsConverter {

    private final CurrenciesDataService currenciesDataService;

    private final PasswordEncoder passwordEncoder;

    private final CurrenciesConverter currenciesConverter;

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
            User e = eOld.cloneShallow();
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
