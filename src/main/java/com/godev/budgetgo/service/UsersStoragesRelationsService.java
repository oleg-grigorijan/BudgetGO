package com.godev.budgetgo.service;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsInfoDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchDto;
import com.godev.budgetgo.entity.UserStorageKey;

import java.util.List;

public interface UsersStoragesRelationsService {
    UserStorageRelationsInfoDto findById(UserStorageKey id);

    List<UserStorageRelationsInfoDto> findByStorageId(Long storageId);

    UserStorageRelationsInfoDto create(Long storageId, UserStorageRelationsCreationDto creationDto);

    UserStorageRelationsInfoDto patch(UserStorageKey id, UserStorageRelationsPatchDto patchDto);

    void deleteById(UserStorageKey id);

}
