package com.godev.budgetgo.service.converter;

import com.godev.budgetgo.dto.CollectionCreationDto;
import com.godev.budgetgo.dto.CollectionInfoDto;
import com.godev.budgetgo.entity.Collection;

public interface CollectionsConverter extends EntityConverter<Collection, CollectionInfoDto>, DtoConverter<CollectionCreationDto, Collection> {
}
