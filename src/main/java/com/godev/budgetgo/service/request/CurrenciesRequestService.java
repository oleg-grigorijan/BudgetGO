package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.CurrencyInfoDto;

import java.util.List;

public interface CurrenciesRequestService {
    CurrencyInfoDto getById(Long id);

    List<CurrencyInfoDto> getAll();
}
