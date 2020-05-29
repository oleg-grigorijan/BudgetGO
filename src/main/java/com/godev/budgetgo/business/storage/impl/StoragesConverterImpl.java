package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.api.rest.storage.dto.StorageCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StoragePatchesDto;
import com.godev.budgetgo.business.currency.CurrenciesConverter;
import com.godev.budgetgo.business.currency.CurrenciesDataService;
import com.godev.budgetgo.business.storage.StorageSettingsConverter;
import com.godev.budgetgo.business.storage.StoragesConverter;
import com.godev.budgetgo.business.storage.StoragesRelationsDataService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import com.godev.budgetgo.infra.error.exception.NotFoundException;
import com.godev.budgetgo.infra.error.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoragesConverterImpl implements StoragesConverter {

    private final StoragesRelationsDataService relationsDataService;

    private final CurrenciesDataService currenciesDataService;

    private final StorageSettingsConverter storageSettingsConverter;

    private final CurrenciesConverter currenciesConverter;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public Storage convertToEntity(StorageCreationDto dto) {
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
    public StorageInfoDto convertToDto(Storage e) {
        StorageInfoDto dto = new StorageInfoDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        dto.setBalance(e.getBalance());
        dto.setCurrencyInfoDto(currenciesConverter.convertToDto(e.getCurrency()));
        dto.setInitialBalance(e.getInitialBalance());
        UserStorageKey userStorageKey = new UserStorageKey(authenticationFacade.getAuthenticatedUser().getId(), e.getId());
        dto.setStorageSettingsInfoDto(storageSettingsConverter.convertToDto(relationsDataService.getById(userStorageKey)));
        return dto;
    }

    @Override
    public Storage merge(Storage eOld, StoragePatchesDto dto) {
        Storage e = eOld.cloneShallow();
        dto.getName().ifPresent(e::setName);
        dto.getDescription().ifPresent(e::setDescription);
        return e;
    }
}
