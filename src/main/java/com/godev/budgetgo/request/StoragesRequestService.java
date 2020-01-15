package com.godev.budgetgo.request;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;

import java.util.List;

public interface StoragesRequestService {
    StorageInfoDto getById(Long id);

    List<StorageInfoDto> getAll();

    StorageInfoDto create(StorageCreationDto creationDto);

    StorageInfoDto patch(Long id, StoragePatchesDto patchesDto);
}
