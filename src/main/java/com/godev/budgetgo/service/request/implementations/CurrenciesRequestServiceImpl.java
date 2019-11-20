package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.service.converter.CurrenciesConverter;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import com.godev.budgetgo.service.request.CurrenciesRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class CurrenciesRequestServiceImpl implements CurrenciesRequestService {

    private final CurrenciesDataService dataService;
    private final CurrenciesConverter converter;

    public CurrenciesRequestServiceImpl(CurrenciesDataService dataService, CurrenciesConverter converter) {
        this.dataService = dataService;
        this.converter = converter;
    }

    @Transactional(readOnly = true)
    @Override
    public CurrencyInfoDto getById(Long id) {
        Currency entity = dataService.getById(id);
        return converter.convertFromEntity(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CurrencyInfoDto> getAll() {
        return converter.convertFromEntities(dataService.getAll());
    }

    @Transactional
    @Override
    public CurrencyInfoDto create(CurrencyCreationDto creationDto) {
        Currency entity = converter.convertFromDto(creationDto);
        Currency savedEntity = dataService.add(entity);
        return converter.convertFromEntity(savedEntity);
    }

    @Transactional
    @Override
    public CurrencyInfoDto patch(Long id, CurrencyPatchesDto patchesDto) {
        Currency entity = dataService.getById(id);
        Currency patchedEntity = converter.merge(entity, patchesDto);
        Currency savedEntity = dataService.update(patchedEntity);
        return converter.convertFromEntity(savedEntity);
    }
}
