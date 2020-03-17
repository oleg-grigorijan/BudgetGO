package com.godev.budgetgo.business.currency.impl;

import com.godev.budgetgo.business.base.BaseDataService;
import com.godev.budgetgo.business.currency.CurrenciesDataService;
import com.godev.budgetgo.domain.currency.Currency;
import com.godev.budgetgo.infra.error.exception.CurrencyNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrenciesDataServiceImpl extends BaseDataService<Currency, Long> implements CurrenciesDataService {

    public CurrenciesDataServiceImpl(CurrenciesRepository repository) {
        super(repository, CurrencyNotFoundException::byId);
    }
}
