package com.godev.budgetgo.converter;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.impl.StoragesConverterImpl;
import com.godev.budgetgo.data.CurrenciesDataService;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class StoragesConverterTest {

    private StoragesConverter converter;

    @Mock
    private StoragesRelationsDataService relationsDataService;

    @Mock
    private CurrenciesDataService currenciesDataService;

    @Mock
    private StorageSettingsConverter storageSettingsConverter;

    @Mock
    private CurrenciesConverter currenciesConverter;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @BeforeEach
    void setUp() {
        converter = new StoragesConverterImpl(
                relationsDataService,
                currenciesDataService,
                storageSettingsConverter,
                currenciesConverter,
                authenticationFacade
        );
    }

    @Test
    void convertToEntity_general_correctReturnValue() {
        StorageCreationDto dto = new StorageCreationDto();
        dto.setName("abc");
        dto.setDescription("def");
        dto.setCurrencyId(1L);
        dto.setInitialBalance(1000L);
        assertThat(dto).hasNoNullFieldsOrProperties();

        Currency currency = new Currency();
        currency.setId(dto.getCurrencyId());
        when(currenciesDataService.getById(dto.getCurrencyId())).thenReturn(currency);

        Storage expectedEntity = new Storage();
        expectedEntity.setName(dto.getName());
        expectedEntity.setDescription(dto.getDescription());
        expectedEntity.setCurrency(currency);
        expectedEntity.setBalance(dto.getInitialBalance());
        expectedEntity.setInitialBalance(dto.getInitialBalance());
        assertThat(expectedEntity).hasNoNullFieldsOrPropertiesExcept("id");

        assertThat(converter.convertToEntity(dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void convertToDto_general_correctReturnValue() {
        Storage entity = new Storage();
        entity.setId(1L);
        entity.setName("abc");
        entity.setDescription("def");
        entity.setCurrency(new Currency());
        entity.getCurrency().setId(2L);
        entity.setBalance(2000L);
        entity.setInitialBalance(1000L);
        assertThat(entity).hasNoNullFieldsOrProperties();

        CurrencyInfoDto currencyInfoDto = new CurrencyInfoDto();
        currencyInfoDto.setId(entity.getCurrency().getId());
        when(currenciesConverter.convertToDto(entity.getCurrency())).thenReturn(currencyInfoDto);
        User authenticatedUser = new User();
        authenticatedUser.setId(3L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);
        StorageRelations relations = new StorageRelations();
        relations.setId(new UserStorageKey(authenticatedUser.getId(), entity.getId()));
        when(relationsDataService.getById(new UserStorageKey(authenticatedUser.getId(), entity.getId()))).thenReturn(relations);
        StorageSettingsInfoDto storageSettingsInfoDto = new StorageSettingsInfoDto();
        when(storageSettingsConverter.convertToDto(relations)).thenReturn(storageSettingsInfoDto);

        StorageInfoDto expectedDto = new StorageInfoDto();
        expectedDto.setId(entity.getId());
        expectedDto.setName(entity.getName());
        expectedDto.setDescription(entity.getDescription());
        expectedDto.setCurrencyInfoDto(currencyInfoDto);
        expectedDto.setBalance(entity.getBalance());
        expectedDto.setInitialBalance(entity.getInitialBalance());
        expectedDto.setStorageSettingsInfoDto(storageSettingsInfoDto);
        assertThat(expectedDto).hasNoNullFieldsOrProperties();

        assertThat(converter.convertToDto(entity)).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void merge_general_correctReturnValue() {
        Storage entity = new Storage();
        entity.setId(1L);
        entity.setName("abc");
        entity.setDescription("def");
        entity.setCurrency(new Currency());
        entity.getCurrency().setId(2L);
        entity.setBalance(2000L);
        entity.setInitialBalance(1000L);
        assertThat(entity).hasNoNullFieldsOrProperties();

        StoragePatchesDto dto = new StoragePatchesDto();
        dto.setName("ABC");
        dto.setDescription("DEF");

        Storage expectedEntity = entity.clone();
        expectedEntity.setName(dto.getName().get());
        expectedEntity.setDescription(dto.getDescription().get());
        assertThat(expectedEntity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void merge_emptyPatchesDto_noEntityChanges() {
        Storage entity = new Storage();
        entity.setId(1L);
        entity.setName("abc");
        entity.setDescription("def");
        entity.setCurrency(new Currency());
        entity.getCurrency().setId(2L);
        entity.setBalance(2000L);
        entity.setInitialBalance(1000L);
        assertThat(entity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, new StoragePatchesDto())).isEqualToComparingFieldByField(entity);
    }
}
