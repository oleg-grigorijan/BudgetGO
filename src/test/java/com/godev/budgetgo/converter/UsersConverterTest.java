package com.godev.budgetgo.converter;

import com.godev.budgetgo.converter.impl.UsersConverterImpl;
import com.godev.budgetgo.data.CurrenciesDataService;
import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersConverterTest {

    private UsersConverter converter;

    @Mock
    private CurrenciesDataService currenciesDataService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        converter = new UsersConverterImpl(currenciesDataService, passwordEncoder);
    }

    @Test
    void convertToEntity_general_correctReturnValue() {
        UserCreationDto dto = new UserCreationDto();
        dto.setPassword("abc");
        dto.setName("def");
        dto.setSurname("ghi");
        dto.setEmail("jkl");
        dto.setLogin("mno");
        dto.setEmailPublic(false);
        dto.setMainCurrencyId(2L);
        assertThat(dto).hasNoNullFieldsOrProperties();

        Currency currency = new Currency();
        currency.setId(dto.getMainCurrencyId());
        when(currenciesDataService.getById(dto.getMainCurrencyId())).thenReturn(currency);
        when(passwordEncoder.encode(anyString())).then(returnsFirstArg());

        User expectedEntity = new User();
        expectedEntity.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        expectedEntity.setName(dto.getName());
        expectedEntity.setSurname(dto.getSurname());
        expectedEntity.setEmail(dto.getEmail());
        expectedEntity.setLogin(dto.getLogin());
        expectedEntity.setEmailPublic(dto.isEmailPublic());
        expectedEntity.setMainCurrency(currency);
        assertThat(expectedEntity).hasNoNullFieldsOrPropertiesExcept("id");

        assertThat(converter.convertToEntity(dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void convertToDto_publicEmail_correctReturnValueWithNotNullEmail() {
        User entity = new User();
        entity.setId(1L);
        entity.setPasswordHash("abc");
        entity.setName("def");
        entity.setSurname("ghi");
        entity.setEmail("jkl");
        entity.setLogin("mno");
        entity.setEmailPublic(true);
        entity.setAdmin(true);
        entity.setMainCurrency(new Currency());
        entity.getMainCurrency().setId(2L);
        assertThat(entity).hasNoNullFieldsOrProperties();

        UserInfoDto expectedDto = new UserInfoDto();
        expectedDto.setId(entity.getId());
        expectedDto.setName(entity.getName());
        expectedDto.setSurname(entity.getSurname());
        expectedDto.setEmail(entity.getEmail());
        expectedDto.setLogin(entity.getLogin());
        expectedDto.setEmailPublic(entity.isEmailPublic());
        assertThat(expectedDto).hasNoNullFieldsOrProperties();

        assertThat(converter.convertToDto(entity)).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void convertToDto_privateEmail_correctReturnValueWithNullEmail() {
        User entity = new User();
        entity.setId(1L);
        entity.setPasswordHash("abc");
        entity.setName("def");
        entity.setSurname("ghi");
        entity.setEmail("jkl");
        entity.setLogin("mno");
        entity.setEmailPublic(false);
        entity.setAdmin(true);
        entity.setMainCurrency(new Currency());
        entity.getMainCurrency().setId(2L);
        assertThat(entity).hasNoNullFieldsOrProperties();

        UserInfoDto expectedDto = new UserInfoDto();
        expectedDto.setId(entity.getId());
        expectedDto.setName(entity.getName());
        expectedDto.setSurname(entity.getSurname());
        expectedDto.setLogin(entity.getLogin());
        expectedDto.setEmailPublic(entity.isEmailPublic());
        assertThat(expectedDto).hasNoNullFieldsOrPropertiesExcept("email");

        assertThat(converter.convertToDto(entity)).isEqualToComparingFieldByField(expectedDto);
    }
}
