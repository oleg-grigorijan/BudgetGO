package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.api.rest.storage.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.domain.storage.UserStorageKey;

import java.util.List;

public interface StoragesRelationsRequestService {

    StorageRelationsInfoDto getById(UserStorageKey id);

    List<StorageRelationsInfoDto> getByStorageId(Long storageId);

    StorageRelationsInfoDto create(ExtendedStorageRelationsCreationDto creationDto);

    StorageRelationsInfoDto patch(UserStorageKey id, StorageRelationsPatchesDto patchesDto);

    void deleteById(UserStorageKey id);
}
