package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.CollectionCreationDto;
import com.godev.budgetgo.entity.Collection;

public interface CollectionsFactory extends ConverterFactory<CollectionCreationDto, Collection> {
}
