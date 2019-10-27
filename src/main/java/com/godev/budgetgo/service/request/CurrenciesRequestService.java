package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;

public interface CurrenciesRequestService
        extends RequestService<Long, CurrencyInfoDto, CurrencyCreationDto, CurrencyPatchesDto> {
}
