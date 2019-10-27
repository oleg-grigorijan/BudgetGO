package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.service.factory.CurrenciesFactory;
import org.springframework.stereotype.Service;

@Service
class CurrenciesFactoryImpl implements CurrenciesFactory {
    @Override
    public Currency createFrom(CurrencyCreationDto dto) {
        Currency e = new Currency();
        e.setName(dto.getName());
        e.setIsoCode(dto.getIsoCode());
        e.setSymbol(dto.getSymbol());
        e.setSymbolPrefixed(dto.isSymbolPrefixed());
        return e;
    }
}
