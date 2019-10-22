package com.godev.budgetgo.service;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;

import java.util.List;

public interface StoragesService {
    StorageInfoDto findById(Long id);

    List<StorageInfoDto> findAll();

    StorageInfoDto create(StorageCreationDto creationDto);

    StorageInfoDto patch(Long id, StoragePatchesDto patchesDto);

    void deleteById(Long id);
}
