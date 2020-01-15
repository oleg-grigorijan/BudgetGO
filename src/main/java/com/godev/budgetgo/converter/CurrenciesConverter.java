package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;

public interface CurrenciesConverter extends ConverterToDto<Currency, CurrencyInfoDto>, ConverterToEntity<CurrencyCreationDto, Currency>,
        Merger<Currency, CurrencyPatchesDto> {
}
