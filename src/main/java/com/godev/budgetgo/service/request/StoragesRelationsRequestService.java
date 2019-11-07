package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.StorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.UserStorageKey;

import java.util.List;

public interface StoragesRelationsRequestService {
    StorageRelationsInfoDto getById(UserStorageKey id);

    List<StorageRelationsInfoDto> getByStorageId(Long storageId);

    StorageRelationsInfoDto create(StorageRelationsCreationDto creationDto);

    StorageRelationsInfoDto patch(UserStorageKey id, StorageRelationsPatchesDto patchesDto);

    void deleteById(UserStorageKey id);
}