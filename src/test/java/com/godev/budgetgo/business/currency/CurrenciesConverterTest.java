package com.godev.budgetgo.business.currency;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyCreationDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyInfoDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyPatchesDto;
import com.godev.budgetgo.business.currency.impl.CurrenciesConverterImpl;
import com.godev.budgetgo.domain.currency.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class CurrenciesConverterTest {

    private CurrenciesConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CurrenciesConverterImpl();
    }

    @Test
    void convertToEntity_general_correctReturnValue() {
        CurrencyCreationDto dto = new CurrencyCreationDto();
        dto.setIsoCode("USD");
        dto.setName("United States Dollar");
        assertThat(dto).hasNoNullFieldsOrProperties();

        Currency expectedEntity = new Currency();
        expectedEntity.setIsoCode(dto.getIsoCode());
        expectedEntity.setName(dto.getName());
        assertThat(expectedEntity).hasNoNullFieldsOrPropertiesExcept("id");

        assertThat(converter.convertToEntity(dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void convertToDto_general_correctReturnValue() {
        Currency entity = new Currency();
        entity.setId(1L);
        entity.setIsoCode("USD");
        entity.setName("United States Dollar");
        assertThat(entity).hasNoNullFieldsOrProperties();

        CurrencyInfoDto expectedDto = new CurrencyInfoDto();
        expectedDto.setId(entity.getId());
        expectedDto.setIsoCode(entity.getIsoCode());
        expectedDto.setName(entity.getName());

        assertThat(converter.convertToDto(entity)).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void merge_general_correctReturnValue() {
        Currency entity = new Currency();
        entity.setId(1L);
        entity.setIsoCode("USD");
        entity.setName("United States Dollar");
        assertThat(entity).hasNoNullFieldsOrProperties();

        CurrencyPatchesDto dto = new CurrencyPatchesDto();
        dto.setIsoCode("BYN");
        dto.setName("Belarusian Ruble");

        Currency expectedEntity = new Currency();
        expectedEntity.setId(entity.getId());
        expectedEntity.setIsoCode(dto.getIsoCode().get());
        expectedEntity.setName(dto.getName().get());

        assertThat(converter.merge(entity, dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void merge_emptyPatchesDto_noEntityChanges() {
        Currency entity = new Currency();
        entity.setId(1L);
        entity.setIsoCode("USD");
        entity.setName("United States Dollar");
        assertThat(entity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, new CurrencyPatchesDto())).isEqualToComparingFieldByField(entity);
    }
}
