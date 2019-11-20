package com.godev.budgetgo.service.converter.implementations;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.exception.NotFoundException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import com.godev.budgetgo.service.converter.CurrenciesConverter;
import com.godev.budgetgo.service.converter.StoragesConverter;
import com.godev.budgetgo.service.data.CurrenciesDataService;
import org.springframework.stereotype.Service;

@Service
class StoragesConverterImpl implements StoragesConverter {

    private final CurrenciesDataService currenciesDataService;
    private final CurrenciesConverter currenciesConverter;

    public StoragesConverterImpl(CurrenciesDataService currenciesDataService, CurrenciesConverter currenciesConverter) {
        this.currenciesDataService = currenciesDataService;
        this.currenciesConverter = currenciesConverter;
    }

    @Override
    public Storage convertFromDto(StorageCreationDto dto) {
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

    @Override
    public StorageInfoDto convertFromEntity(Storage e) {
        StorageInfoDto dto = new StorageInfoDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        dto.setBalance(e.getBalance());
        dto.setCurrencyInfoDto(currenciesConverter.convertFromEntity(e.getCurrency()));
        dto.setInitialBalance(e.getInitialBalance());
        return dto;
    }

    @Override
    public Storage merge(Storage eOld, StoragePatchesDto dto) {
        Storage e = eOld.clone();
        dto.getName().ifPresent(e::setName);
        dto.getDescription().ifPresent(e::setDescription);
        return e;
    }
}
