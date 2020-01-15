package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;

public interface StoragesConverter extends ConverterToDto<Storage, StorageInfoDto>, ConverterToEntity<StorageCreationDto, Storage>,
        Merger<Storage, StoragePatchesDto> {
}
