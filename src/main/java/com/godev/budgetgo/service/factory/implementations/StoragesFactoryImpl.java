package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.exception.NotFoundException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import com.godev.budgetgo.service.factory.StoragesFactory;
import org.springframework.stereotype.Service;

@Service
class StoragesFactoryImpl implements StoragesFactory {

    private final CurrenciesDataService currenciesDataService;

    public StoragesFactoryImpl(CurrenciesDataService currenciesDataService) {
        this.currenciesDataService = currenciesDataService;
    }

    @Override
    public Storage createFrom(StorageCreationDto dto) {
        try {
            Storage e = new Storage();
            e.setCurrency(currenciesDataService.getById(dto.getCurrencyId()));
            e.setName(dto.getName());
            e.setDescription(dto.getDescription());
            e.setBalance(dto.getInitialBalance());
            e.setInitialBalance(dto.getInitialBalance());
            return e;

        } catch (NotFoundException ex) {
            throw new UnprocessableEntityException(ex);
        }
    }
}
