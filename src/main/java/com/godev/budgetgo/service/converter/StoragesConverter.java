package com.godev.budgetgo.service.converter;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;

public interface StoragesConverter extends EntityConverter<Storage, StorageInfoDto>, DtoConverter<StorageCreationDto, Storage>,
        Merger<Storage, StoragePatchesDto> {
}
