package com.godev.budgetgo.request;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;

import java.util.List;

public interface CurrenciesRequestService {
    CurrencyInfoDto getById(Long id);

    List<CurrencyInfoDto> getAll();

    CurrencyInfoDto create(CurrencyCreationDto creationDto);

    CurrencyInfoDto patch(Long id, CurrencyPatchesDto patchesDto);
}
