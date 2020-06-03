package com.godev.budgetgo.business.currency;

import com.godev.budgetgo.api.rest.currency.dto.CurrencyCreationDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyInfoDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyPatchesDto;
import com.godev.budgetgo.business.base.ConverterToDto;
import com.godev.budgetgo.business.base.ConverterToEntity;
import com.godev.budgetgo.business.base.Merger;
import com.godev.budgetgo.domain.currency.Currency;

public interface CurrenciesConverter extends ConverterToDto<Currency, CurrencyInfoDto>, ConverterToEntity<CurrencyCreationDto, Currency>,
        Merger<Currency, CurrencyPatchesDto> {
}
