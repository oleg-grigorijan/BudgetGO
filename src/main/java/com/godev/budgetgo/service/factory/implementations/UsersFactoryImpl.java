package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.NotFoundException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import com.godev.budgetgo.service.factory.UsersFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class UsersFactoryImpl implements UsersFactory {

    private final CurrenciesDataService currenciesDataService;
    private final PasswordEncoder passwordEncoder;

    public UsersFactoryImpl(CurrenciesDataService currenciesDataService, PasswordEncoder passwordEncoder) {
        this.currenciesDataService = currenciesDataService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createFrom(UserCreationDto dto) {
        try {
            User user = new User();
            user.setMainCurrency(currenciesDataService.getById(dto.getMainCurrencyId()));
            user.setLogin(dto.getLogin());
            user.setEmail(dto.getEmail());
            user.setName(dto.getName());
            user.setSurname(dto.getSurname());
            user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
            user.setEmailPublic(dto.isEmailPublic());
            return user;

        } catch (NotFoundException ex) {
            throw new UnprocessableEntityException(ex);
        }
    }
}
