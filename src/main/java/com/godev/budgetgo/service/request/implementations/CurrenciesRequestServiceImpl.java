package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import com.godev.budgetgo.service.factory.CurrencyDtoFactory;
import com.godev.budgetgo.service.request.CurrenciesRequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CurrenciesRequestServiceImpl implements CurrenciesRequestService {

    private final CurrenciesDataService dataService;
    private final CurrencyDtoFactory dtoFactory;

    public CurrenciesRequestServiceImpl(
            CurrenciesDataService dataService,
            CurrencyDtoFactory dtoFactory
    ) {
        this.dataService = dataService;
        this.dtoFactory = dtoFactory;
    }

    @Override
    public CurrencyInfoDto getById(Long id) {
        return dtoFactory.createFrom(dataService.getById(id));
    }

    @Override
    public List<CurrencyInfoDto> getAll() {
        return dataService
                .getAll()
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }
}
