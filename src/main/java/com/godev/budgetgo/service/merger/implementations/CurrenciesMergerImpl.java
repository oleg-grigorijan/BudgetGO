package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.service.merger.CurrenciesMerger;
import org.springframework.stereotype.Service;

@Service
class CurrenciesMergerImpl implements CurrenciesMerger {
    @Override
    public Currency merge(CurrencyPatchesDto dto, Currency eOld) {
        Currency e = eOld.clone();
        dto.getName().ifPresent(e::setName);
        dto.getIsoCode().ifPresent(e::setIsoCode);
        dto.getSymbol().ifPresent(e::setSymbol);
        dto.getSymbolPrefixed().ifPresent(e::setSymbolPrefixed);
        return e;
    }
}
