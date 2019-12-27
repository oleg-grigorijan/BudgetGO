package com.godev.budgetgo.data.impl;

import com.godev.budgetgo.data.CurrenciesDataService;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.exception.CurrencyNotFoundException;
import com.godev.budgetgo.repository.CurrenciesRepository;
import org.springframework.stereotype.Service;

@Service
class CurrenciesDataServiceImpl extends AbstractDataService<Currency, Long> implements CurrenciesDataService {

    public CurrenciesDataServiceImpl(CurrenciesRepository repository) {
        super(repository, CurrencyNotFoundException::byId);
    }
}
