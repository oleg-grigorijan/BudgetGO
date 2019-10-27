package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.entity.Currency;

public interface CurrenciesFactory extends ConverterFactory<CurrencyCreationDto, Currency> {
}
