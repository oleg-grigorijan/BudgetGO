package com.godev.budgetgo.business.currency.impl;

import com.godev.budgetgo.api.rest.currency.dto.CurrencyCreationDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyInfoDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyPatchesDto;
import com.godev.budgetgo.business.currency.CurrenciesConverter;
import com.godev.budgetgo.business.currency.CurrenciesDataService;
import com.godev.budgetgo.business.currency.CurrenciesRequestService;
import com.godev.budgetgo.domain.currency.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrenciesRequestServiceImpl implements CurrenciesRequestService {

    private final CurrenciesDataService dataService;
    private final CurrenciesConverter converter;

    @Transactional(readOnly = true)
    @Override
    public CurrencyInfoDto getById(Long id) {
        Currency entity = dataService.getById(id);
        return converter.convertToDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CurrencyInfoDto> getAll() {
        return converter.convertToDtos(dataService.getAll());
    }

    @Transactional
    @Override
    public CurrencyInfoDto create(CurrencyCreationDto creationDto) {
        Currency entity = converter.convertToEntity(creationDto);
        Currency savedEntity = dataService.add(entity);
        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public CurrencyInfoDto patch(Long id, CurrencyPatchesDto patchesDto) {
        Currency entity = dataService.getById(id);
        Currency patchedEntity = converter.merge(entity, patchesDto);
        Currency savedEntity = dataService.update(patchedEntity);
        return converter.convertToDto(savedEntity);
    }
}
