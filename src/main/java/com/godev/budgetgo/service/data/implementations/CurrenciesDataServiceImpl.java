package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.exception.CurrencyNotFoundException;
import com.godev.budgetgo.repository.CurrenciesRepository;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import org.springframework.stereotype.Service;

@Service
class CurrenciesDataServiceImpl extends AbstractDataService<Currency, Long> implements CurrenciesDataService {

    public CurrenciesDataServiceImpl(CurrenciesRepository repository) {
        super(repository, CurrencyNotFoundException::byId);
    }
}
