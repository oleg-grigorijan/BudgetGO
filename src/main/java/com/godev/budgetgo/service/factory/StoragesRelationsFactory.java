package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.StorageRelationsCreationDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;

public interface StoragesRelationsFactory
        extends ConverterFactory<StorageRelationsCreationDto, StorageRelations> {

    StorageRelations generateCreatorEntityForStorage(Storage storage);
}
