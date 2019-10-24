package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.UserStorageRelationsInfoDto;
import com.godev.budgetgo.entity.UserStorageRelations;

public interface UserStorageRelationsDtoFactory
        extends ConverterFactory<UserStorageRelations, UserStorageRelationsInfoDto> {
}
