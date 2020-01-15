package com.godev.budgetgo.converter;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.converter.impl.UserSettingsConverterImpl;
import com.godev.budgetgo.data.CurrenciesDataService;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
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
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class UserSettingsConverterTest {

    private UserSettingsConverter converter;

    @Mock
    private CurrenciesDataService currenciesDaraService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CurrenciesConverter currenciesConverter;

    @BeforeEach
    void setUp() {
        converter = new UserSettingsConverterImpl(currenciesDaraService, passwordEncoder, currenciesConverter);
    }

    @Test
    void convertToDto_general_correctReturnValue() {
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

        CurrencyInfoDto mainCurrencyInfoDto = new CurrencyInfoDto();
        mainCurrencyInfoDto.setId(entity.getMainCurrency().getId());
        when(currenciesConverter.convertToDto(entity.getMainCurrency())).thenReturn(mainCurrencyInfoDto);

        UserSettingsInfoDto expectedDto = new UserSettingsInfoDto();
        expectedDto.setId(entity.getId());
        expectedDto.setName(entity.getName());
        expectedDto.setSurname(entity.getSurname());
        expectedDto.setEmail(entity.getEmail());
        expectedDto.setLogin(entity.getLogin());
        expectedDto.setEmailPublic(entity.isEmailPublic());
        expectedDto.setMainCurrencyInfoDto(mainCurrencyInfoDto);
        assertThat(expectedDto).hasNoNullFieldsOrProperties();

        assertThat(converter.convertToDto(entity)).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void merge_general_correctReturnValue() {
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

        UserSettingsPatchesDto dto = new UserSettingsPatchesDto();
        dto.setPassword("ABC");
        dto.setName("DEF");
        dto.setSurname("GHI");
        dto.setEmail("JKL");
        dto.setLogin("MNO");
        dto.setEmailPublic(true);
        dto.setMainCurrencyId(3L);

        when(passwordEncoder.encode(anyString())).then(returnsFirstArg());
        Currency newMainCurrency = new Currency();
        newMainCurrency.setId(dto.getMainCurrencyId().get());
        when(currenciesDaraService.getById(dto.getMainCurrencyId().get())).thenReturn(newMainCurrency);

        User expectedEntity = new User();
        expectedEntity.setId(entity.getId());
        expectedEntity.setPasswordHash(passwordEncoder.encode(dto.getPassword().get()));
        expectedEntity.setName(dto.getName().get());
        expectedEntity.setSurname(dto.getSurname().get());
        expectedEntity.setEmail(dto.getEmail().get());
        expectedEntity.setLogin(dto.getLogin().get());
        expectedEntity.setEmailPublic(dto.getEmailPublic().get());
        expectedEntity.setAdmin(entity.isAdmin());
        expectedEntity.setMainCurrency(newMainCurrency);
        assertThat(expectedEntity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void merge_emptyPatchesDto_noEntityChanges() {
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

        assertThat(converter.merge(entity, new UserSettingsPatchesDto())).isEqualToComparingFieldByField(entity);
    }
}
