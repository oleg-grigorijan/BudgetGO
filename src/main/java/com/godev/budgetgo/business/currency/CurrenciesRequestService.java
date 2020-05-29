package com.godev.budgetgo.business.currency;

import com.godev.budgetgo.api.rest.currency.dto.CurrencyCreationDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyInfoDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyPatchesDto;

import java.util.List;

public interface CurrenciesRequestService {

    CurrencyInfoDto getById(Long id);

    List<CurrencyInfoDto> getAll();

    CurrencyInfoDto create(CurrencyCreationDto creationDto);

    CurrencyInfoDto patch(Long id, CurrencyPatchesDto patchesDto);
}
