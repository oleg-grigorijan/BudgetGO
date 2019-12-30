package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;

public interface StorageRelationsConverter extends ConverterToDto<StorageRelations, StorageRelationsInfoDto>,
        ConverterToEntity<ExtendedStorageRelationsCreationDto, StorageRelations>, Merger<StorageRelations, StorageRelationsPatchesDto> {
}
