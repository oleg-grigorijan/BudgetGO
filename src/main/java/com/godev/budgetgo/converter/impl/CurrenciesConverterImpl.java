package com.godev.budgetgo.converter.impl;

import com.godev.budgetgo.converter.CurrenciesConverter;
import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;
import org.springframework.stereotype.Service;

@Service
public class CurrenciesConverterImpl implements CurrenciesConverter {
    @Override
    public Currency convertToEntity(CurrencyCreationDto dto) {
        Currency e = new Currency();
        e.setName(dto.getName());
        e.setIsoCode(dto.getIsoCode());
        return e;
    }

    @Override
    public CurrencyInfoDto convertToDto(Currency e) {
        CurrencyInfoDto dto = new CurrencyInfoDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setIsoCode(e.getIsoCode());
        return dto;
    }

    @Override
    public Currency merge(Currency eOld, CurrencyPatchesDto dto) {
        Currency e = eOld.cloneShallow();
        dto.getName().ifPresent(e::setName);
        dto.getIsoCode().ifPresent(e::setIsoCode);
        return e;
    }
}
