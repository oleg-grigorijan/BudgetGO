package com.godev.budgetgo.service.converter;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;

public interface CurrenciesConverter extends EntityConverter<Currency, CurrencyInfoDto>, DtoConverter<CurrencyCreationDto, Currency>,
        Merger<Currency, CurrencyPatchesDto> {
}
