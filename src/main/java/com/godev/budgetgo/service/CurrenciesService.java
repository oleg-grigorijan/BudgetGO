package com.godev.budgetgo.service;

import com.godev.budgetgo.dto.CurrencyInfoDto;

import java.util.List;

public interface CurrenciesService {
    CurrencyInfoDto findById(Long id);

    List<CurrencyInfoDto> findAll();
}
