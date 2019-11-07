package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.entity.StorageRelations;

public interface StorageRelationsDtoFactory
        extends ConverterFactory<StorageRelations, StorageRelationsInfoDto> {
}
