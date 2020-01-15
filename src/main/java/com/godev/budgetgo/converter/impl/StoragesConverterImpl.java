package com.godev.budgetgo.converter.impl;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.CurrenciesConverter;
import com.godev.budgetgo.converter.StorageSettingsConverter;
import com.godev.budgetgo.converter.StoragesConverter;
import com.godev.budgetgo.data.CurrenciesDataService;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.exception.NotFoundException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import org.springframework.stereotype.Service;

@Service
public class StoragesConverterImpl implements StoragesConverter {

    private final StoragesRelationsDataService relationsDataService;
    private final CurrenciesDataService currenciesDataService;
    private final StorageSettingsConverter storageSettingsConverter;
    private final CurrenciesConverter currenciesConverter;
    private final AuthenticationFacade authenticationFacade;

    public StoragesConverterImpl(
            StoragesRelationsDataService relationsDataService,
            CurrenciesDataService currenciesDataService,
            StorageSettingsConverter storageSettingsConverter,
            CurrenciesConverter currenciesConverter,
            AuthenticationFacade authenticationFacade
    ) {
        this.relationsDataService = relationsDataService;
        this.currenciesDataService = currenciesDataService;
        this.storageSettingsConverter = storageSettingsConverter;
        this.currenciesConverter = currenciesConverter;
        this.authenticationFacade = authenticationFacade;
    }

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
