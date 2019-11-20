package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.converter.CurrenciesConverter;
import com.godev.budgetgo.service.factory.StorageDtoFactory;
import org.springframework.stereotype.Service;

@Service
class StorageDtoFactoryImpl implements StorageDtoFactory {

    private final CurrenciesConverter currenciesConverter;

    public StorageDtoFactoryImpl(CurrenciesConverter currenciesConverter) {
        this.currenciesConverter = currenciesConverter;
    }

    @Override
    public StorageInfoDto createFrom(Storage e) {
        StorageInfoDto dto = new StorageInfoDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        dto.setBalance(e.getBalance());
        dto.setCurrencyInfoDto(currenciesConverter.convertFromEntity(e.getCurrency()));
        dto.setInitialBalance(e.getInitialBalance());
        return dto;
    }
}
