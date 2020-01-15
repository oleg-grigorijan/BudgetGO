package com.godev.budgetgo.converter.impl;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.StorageRelationsConverter;
import com.godev.budgetgo.converter.UsersConverter;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.exception.NotFoundException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import org.springframework.stereotype.Service;

@Service
public class StorageRelationsConverterImpl implements StorageRelationsConverter {

    private final StoragesDataService storagesDataService;
    private final UsersDataService usersDataService;
    private final AuthenticationFacade authenticationFacade;
    private final UsersConverter usersConverter;

    public StorageRelationsConverterImpl(
            StoragesDataService storagesDataService,
            UsersDataService usersDataService,
            AuthenticationFacade authenticationFacade,
            UsersConverter usersConverter
    ) {
        this.storagesDataService = storagesDataService;
        this.usersDataService = usersDataService;
        this.authenticationFacade = authenticationFacade;
        this.usersConverter = usersConverter;
    }

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
