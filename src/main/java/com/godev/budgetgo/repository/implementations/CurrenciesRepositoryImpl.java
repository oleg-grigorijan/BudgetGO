package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.repository.CurrenciesRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CurrenciesRepositoryImpl extends AbstractRepository<Currency> implements CurrenciesRepository {
    CurrenciesRepositoryImpl() {
        super(Currency.class);
    }
}
