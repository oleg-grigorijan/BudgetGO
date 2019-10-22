package com.godev.budgetgo.service.implementations;

import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.exception.CurrencyNotFoundException;
import com.godev.budgetgo.repository.CurrenciesRepository;
import com.godev.budgetgo.service.CurrenciesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrenciesServiceImpl implements CurrenciesService {

    private final CurrenciesRepository currenciesRepository;

    public CurrenciesServiceImpl(CurrenciesRepository currenciesRepository) {
        this.currenciesRepository = currenciesRepository;
    }

    @Override
    public CurrencyInfoDto findById(Long id) {
        return currenciesRepository
                .findById(id)
                .map(CurrencyInfoDto::from)
                .orElseThrow(CurrencyNotFoundException::new);
    }

    @Override
    public List<CurrencyInfoDto> findAll() {
        return currenciesRepository
                .findAll()
                .stream()
                .map(CurrencyInfoDto::from)
                .collect(Collectors.toList());
    }
}
