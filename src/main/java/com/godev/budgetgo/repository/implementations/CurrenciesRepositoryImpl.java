package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.repository.CurrenciesRepository;
import org.springframework.stereotype.Repository;

@Repository
class CurrenciesRepositoryImpl extends AbstractRepository<Currency, Long> implements CurrenciesRepository {

    CurrenciesRepositoryImpl() {
        super(Currency.class);
    }
}
