package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.factory.CurrencyDtoFactory;
import com.godev.budgetgo.service.factory.StorageDtoFactory;
import org.springframework.stereotype.Service;

@Service
class StorageDtoFactoryImpl implements StorageDtoFactory {
    private final CurrencyDtoFactory currencyDtoConverter;

    public StorageDtoFactoryImpl(CurrencyDtoFactory currencyDtoConverter) {
        this.currencyDtoConverter = currencyDtoConverter;
    }

    @Override
    public StorageInfoDto createFrom(Storage e) {
        StorageInfoDto dto = new StorageInfoDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        dto.setBalance(e.getBalance());
        dto.setCurrencyInfoDto(currencyDtoConverter.createFrom(e.getCurrency()));
        dto.setInitialBalance(e.getInitialBalance());
        return dto;
    }
}
