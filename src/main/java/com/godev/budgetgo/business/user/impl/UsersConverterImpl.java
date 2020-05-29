package com.godev.budgetgo.business.user.impl;

import com.godev.budgetgo.api.rest.user.dto.UserCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserInfoDto;
import com.godev.budgetgo.business.currency.CurrenciesDataService;
import com.godev.budgetgo.business.user.UsersConverter;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.error.exception.NotFoundException;
import com.godev.budgetgo.infra.error.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersConverterImpl implements UsersConverter {

    private final CurrenciesDataService currenciesDataService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convertToEntity(UserCreationDto dto) {
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

    @Override
    public UserInfoDto convertToDto(User e) {
        UserInfoDto dto = new UserInfoDto();
        dto.setId(e.getId());
        dto.setLogin(e.getLogin());
        dto.setEmailPublic(e.isEmailPublic());
        if (e.isEmailPublic()) {
            dto.setEmail(e.getEmail());
        }
        dto.setName(e.getName());
        dto.setSurname(e.getSurname());
        return dto;
    }
}
