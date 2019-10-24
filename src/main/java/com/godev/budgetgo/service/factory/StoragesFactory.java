package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.entity.Storage;

public interface StoragesFactory extends ConverterFactory<StorageCreationDto, Storage> {
}
