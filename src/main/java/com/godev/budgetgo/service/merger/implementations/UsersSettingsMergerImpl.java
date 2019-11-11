package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import com.godev.budgetgo.service.merger.UsersSettingsMerger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class UsersSettingsMergerImpl implements UsersSettingsMerger {

    private final CurrenciesDataService currenciesDataService;
    private final PasswordEncoder passwordEncoder;

    public UsersSettingsMergerImpl(
            CurrenciesDataService currenciesDataService,
            PasswordEncoder passwordEncoder
    ) {
        this.currenciesDataService = currenciesDataService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User merge(UserSettingsPatchesDto dto, User eOld) {
        User e = eOld.clone();
        dto.getLogin().ifPresent(e::setLogin);
        dto.getEmail().ifPresent(e::setEmail);
        dto.getName().ifPresent(e::setName);
        dto.getSurname().ifPresent(e::setSurname);
        dto.getPassword().ifPresent(
                password -> e.setPasswordHash(passwordEncoder.encode(password))
        );
        dto.getEmailPublic().ifPresent(e::setEmailPublic);
        dto.getMainCurrencyId().ifPresent(
                currencyId -> e.setMainCurrency(currenciesDataService.getById(currencyId))
        );
        return e;
    }
}
