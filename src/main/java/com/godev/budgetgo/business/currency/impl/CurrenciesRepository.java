package com.godev.budgetgo.business.currency.impl;

import com.godev.budgetgo.domain.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrenciesRepository extends JpaRepository<Currency, Long> {
}
