package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;

public interface CurrenciesAuthorizationService
        extends AuthorizationService<Currency, CurrencyCreationDto, CurrencyPatchesDto> {
}
