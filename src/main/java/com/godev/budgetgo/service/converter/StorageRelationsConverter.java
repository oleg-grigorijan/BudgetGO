package com.godev.budgetgo.service.converter;

import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;

public interface StorageRelationsConverter extends EntityConverter<StorageRelations, StorageRelationsInfoDto>,
        DtoConverter<ExtendedStorageRelationsCreationDto, StorageRelations>, Merger<StorageRelations, StorageRelationsPatchesDto> {
}
