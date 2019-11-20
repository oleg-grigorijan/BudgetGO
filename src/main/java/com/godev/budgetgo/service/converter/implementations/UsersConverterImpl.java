package com.godev.budgetgo.service.converter.implementations;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.NotFoundException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import com.godev.budgetgo.service.converter.UsersConverter;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class UsersConverterImpl implements UsersConverter {

    private final CurrenciesDataService currenciesDataService;
    private final PasswordEncoder passwordEncoder;

    public UsersConverterImpl(CurrenciesDataService currenciesDataService, PasswordEncoder passwordEncoder) {
        this.currenciesDataService = currenciesDataService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User convertFromDto(UserCreationDto dto) {
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
    public UserInfoDto convertFromEntity(User e) {
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
