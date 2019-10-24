package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.entity.Currency;

public interface CurrencyDtoFactory extends ConverterFactory<Currency, CurrencyInfoDto> {
}
