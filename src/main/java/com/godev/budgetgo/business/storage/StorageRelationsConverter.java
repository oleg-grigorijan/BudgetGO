package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.api.rest.storage.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.business.base.ConverterToDto;
import com.godev.budgetgo.business.base.ConverterToEntity;
import com.godev.budgetgo.business.base.Merger;
import com.godev.budgetgo.domain.storage.StorageRelations;

public interface StorageRelationsConverter extends ConverterToDto<StorageRelations, StorageRelationsInfoDto>,
        ConverterToEntity<ExtendedStorageRelationsCreationDto, StorageRelations>, Merger<StorageRelations, StorageRelationsPatchesDto> {
}
