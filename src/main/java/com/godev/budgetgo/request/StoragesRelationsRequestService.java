package com.godev.budgetgo.request;

import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.UserStorageKey;

import java.util.List;

public interface StoragesRelationsRequestService {
    StorageRelationsInfoDto getById(UserStorageKey id);

    List<StorageRelationsInfoDto> getByStorageId(Long storageId);

    StorageRelationsInfoDto create(ExtendedStorageRelationsCreationDto creationDto);

    StorageRelationsInfoDto patch(UserStorageKey id, StorageRelationsPatchesDto patchesDto);

    void deleteById(UserStorageKey id);
}
