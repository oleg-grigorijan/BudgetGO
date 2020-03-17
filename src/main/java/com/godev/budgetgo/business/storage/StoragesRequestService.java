package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.api.rest.storage.dto.StorageCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StoragePatchesDto;

import java.util.List;

public interface StoragesRequestService {

    StorageInfoDto getById(Long id);

    List<StorageInfoDto> getAll();

    StorageInfoDto create(StorageCreationDto creationDto);

    StorageInfoDto patch(Long id, StoragePatchesDto patchesDto);
}
