package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsInfoDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchesDto;
import com.godev.budgetgo.entity.UserStorageKey;

import java.util.List;

public interface UsersStoragesRelationsRequestService {
    UserStorageRelationsInfoDto getById(UserStorageKey id);

    List<UserStorageRelationsInfoDto> getByStorageId(Long storageId);

    UserStorageRelationsInfoDto create(UserStorageRelationsCreationDto creationDto);

    UserStorageRelationsInfoDto patch(UserStorageKey id, UserStorageRelationsPatchesDto patchesDto);

    void deleteById(UserStorageKey id);
}