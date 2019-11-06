package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.service.authorization.CurrenciesAuthorizationService;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import com.godev.budgetgo.service.factory.CurrenciesFactory;
import com.godev.budgetgo.service.factory.CurrencyDtoFactory;
import com.godev.budgetgo.service.merger.CurrenciesMerger;
import com.godev.budgetgo.service.request.CurrenciesRequestService;
import org.springframework.stereotype.Service;

@Service
class CurrenciesRequestServiceImpl
        extends AbstractRequestService<Currency, Long, CurrencyInfoDto, CurrencyCreationDto, CurrencyPatchesDto>
        implements CurrenciesRequestService {

    public CurrenciesRequestServiceImpl(
            CurrenciesDataService dataService,
            CurrenciesFactory entitiesFactory,
            CurrencyDtoFactory dtoFactory,
            CurrenciesMerger merger,
            CurrenciesAuthorizationService authorizationService) {
        super(dataService, entitiesFactory, dtoFactory, merger, authorizationService);
    }
}
