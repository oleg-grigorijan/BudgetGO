package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;

public interface StoragesRelationsFactory extends ConverterFactory<ExtendedStorageRelationsCreationDto, StorageRelations> {
    StorageRelations createCreatorEntityForStorage(Storage storage);
}
