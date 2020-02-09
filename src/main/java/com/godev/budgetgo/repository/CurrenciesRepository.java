package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrenciesRepository extends JpaRepository<Currency, Long> {
}
