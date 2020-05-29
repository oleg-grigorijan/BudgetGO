package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.api.rest.storage.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.business.storage.StorageRelationsConverter;
import com.godev.budgetgo.business.storage.StoragesDataService;
import com.godev.budgetgo.business.user.UsersConverter;
import com.godev.budgetgo.business.user.UsersDataService;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import com.godev.budgetgo.infra.error.exception.NotFoundException;
import com.godev.budgetgo.infra.error.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageRelationsConverterImpl implements StorageRelationsConverter {

    private final StoragesDataService storagesDataService;

    private final UsersDataService usersDataService;

    private final AuthenticationFacade authenticationFacade;

    private final UsersConverter usersConverter;

    @Override
    public StorageRelations convertToEntity(ExtendedStorageRelationsCreationDto dto) {
        try {
            StorageRelations e = new StorageRelations();
            e.setStorage(storagesDataService.getById(dto.getStorageId()));
            e.setUser(usersDataService.getById(dto.getUserId()));
            e.setId(new UserStorageKey(dto.getUserId(), dto.getStorageId()));
            e.setUserRole(dto.getUserRole());
            e.setIncludedInUserStatistics(false);
            e.setInviter(authenticationFacade.getAuthenticatedUser());
            e.setInvitation(true);
            return e;

        } catch (NotFoundException ex) {
            throw new UnprocessableEntityException(ex);
        }
    }

    @Override
    public StorageRelationsInfoDto convertToDto(StorageRelations e) {
        StorageRelationsInfoDto dto = new StorageRelationsInfoDto();
        dto.setUserInfoDto(usersConverter.convertToDto(e.getUser()));
        dto.setUserRole(e.getUserRole());
        dto.setInviterInfoDto(usersConverter.convertToDto(e.getInviter()));
        return dto;
    }

    @Override
    public StorageRelations merge(StorageRelations eOld, StorageRelationsPatchesDto dto) {
        StorageRelations e = eOld.cloneShallow();
        dto.getUserRole().ifPresent(e::setUserRole);
        return e;
    }
}
