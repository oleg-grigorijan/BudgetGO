package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import com.godev.budgetgo.service.factory.CurrenciesFactory;
import com.godev.budgetgo.service.factory.CurrencyDtoFactory;
import com.godev.budgetgo.service.merger.CurrenciesMerger;
import com.godev.budgetgo.service.request.CurrenciesRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CurrenciesRequestServiceImpl implements CurrenciesRequestService {

    private final CurrenciesDataService dataService;
    private final CurrenciesFactory entitiesFactory;
    private final CurrencyDtoFactory dtoFactory;
    private final CurrenciesMerger merger;

    public CurrenciesRequestServiceImpl(
            CurrenciesDataService dataService,
            CurrenciesFactory entitiesFactory,
            CurrencyDtoFactory dtoFactory,
            CurrenciesMerger merger
    ) {
        this.dataService = dataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
    }

    @Override
    public CurrencyInfoDto getById(Long id) {
        Currency entity = dataService.getById(id);
        return dtoFactory.createFrom(entity);
    }

    @Override
    public List<CurrencyInfoDto> getAll() {
        return dataService
                .getAll()
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CurrencyInfoDto create(CurrencyCreationDto creationDto) {
        Currency entity = entitiesFactory.createFrom(creationDto);
        // TODO: Validation
        Currency savedEntity = dataService.add(entity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public CurrencyInfoDto patch(Long id, CurrencyPatchesDto patchesDto) {
        Currency entity = dataService.getById(id);
        Currency patchedEntity = merger.merge(patchesDto, entity);
        // TODO: Validation
        Currency savedEntity = dataService.update(patchedEntity);
        return dtoFactory.createFrom(savedEntity);
    }
}
