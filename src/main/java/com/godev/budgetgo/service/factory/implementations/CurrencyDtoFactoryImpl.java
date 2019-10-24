package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.service.factory.CurrencyDtoFactory;
import org.springframework.stereotype.Service;

@Service
class CurrencyDtoFactoryImpl implements CurrencyDtoFactory {
    @Override
    public CurrencyInfoDto createFrom(Currency e) {
        CurrencyInfoDto dto = new CurrencyInfoDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setIsoCode(e.getIsoCode());
        dto.setSymbol(e.getSymbol());
        dto.setSymbolPrefixed(e.isSymbolPrefixed());
        return dto;
    }
}
