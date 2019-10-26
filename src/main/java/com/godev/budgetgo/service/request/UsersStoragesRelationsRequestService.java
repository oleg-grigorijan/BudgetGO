package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsInfoDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchDto;
import com.godev.budgetgo.entity.UserStorageKey;

import java.util.List;

public interface UsersStoragesRelationsRequestService
        extends RequestService<UserStorageKey, UserStorageRelationsInfoDto, UserStorageRelationsCreationDto, UserStorageRelationsPatchDto> {
    List<UserStorageRelationsInfoDto> getByStorageId(Long storageId);

    void deleteById(UserStorageKey id);
}