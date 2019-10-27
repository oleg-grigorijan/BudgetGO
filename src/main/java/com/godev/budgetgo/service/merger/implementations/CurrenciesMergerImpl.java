package com.godev.budgetgo.service.merger.implementations;

import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.service.merger.CurrenciesMerger;
import org.springframework.stereotype.Service;

@Service
public class CurrenciesMergerImpl implements CurrenciesMerger {
    @Override
    public Currency merge(CurrencyPatchesDto dto, Currency eOld) {
        Currency e = eOld.clone();
        if (dto.getName() != null) e.setName(dto.getName());
        if (dto.getIsoCode() != null) e.setIsoCode(dto.getIsoCode());
        if (dto.getSymbol() != null) e.setSymbol(dto.getSymbol());
        if (dto.getSymbolPrefixed() != null) e.setSymbolPrefixed(dto.getSymbolPrefixed());
        return e;
    }
}
