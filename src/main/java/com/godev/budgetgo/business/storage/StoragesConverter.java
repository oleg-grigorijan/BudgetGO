package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.api.rest.storage.dto.StorageCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StoragePatchesDto;
import com.godev.budgetgo.business.base.ConverterToDto;
import com.godev.budgetgo.business.base.ConverterToEntity;
import com.godev.budgetgo.business.base.Merger;
import com.godev.budgetgo.domain.storage.Storage;

public interface StoragesConverter extends ConverterToDto<Storage, StorageInfoDto>, ConverterToEntity<StorageCreationDto, Storage>,
        Merger<Storage, StoragePatchesDto> {
}
