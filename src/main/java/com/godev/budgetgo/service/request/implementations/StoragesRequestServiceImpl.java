package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.factory.StorageDtoFactory;
import com.godev.budgetgo.service.factory.StoragesFactory;
import com.godev.budgetgo.service.merger.StoragesMerger;
import com.godev.budgetgo.service.request.StoragesRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class StoragesRequestServiceImpl implements StoragesRequestService {

    private final StoragesDataService dataService;
    private final StoragesFactory entitiesFactory;
    private final StorageDtoFactory dtoFactory;
    private final StoragesMerger merger;
    private final StoragesAuthorizationService authorizationService;
    private final AuthenticationFacade authenticationFacade;


    public StoragesRequestServiceImpl(
            StoragesDataService dataService,
            StoragesFactory entitiesFactory,
            StorageDtoFactory dtoFactory,
            StoragesMerger merger,
            StoragesAuthorizationService authorizationService,
            AuthenticationFacade authenticationFacade
    ) {
        this.dataService = dataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
        this.authorizationService = authorizationService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public StorageInfoDto getById(Long id) {
        Storage entity = dataService.getById(id);
        authorizationService.authorizeAccess(entity);
        return dtoFactory.createFrom(entity);
    }

    @Override
    public List<StorageInfoDto> getAll() {
        return authorizationService
                .getAllAuthorizedEntities()
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public StorageInfoDto patch(Long id, StoragePatchesDto patchesDto) {
        Storage entity = dataService.getById(id);
        authorizationService.authorizeModificationAccess(entity);
        Storage patchedEntity = merger.merge(patchesDto, entity);
        // TODO: Validation
        Storage savedEntity = dataService.update(patchedEntity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public StorageInfoDto create(StorageCreationDto creationDto) {
        // TODO: Move to UserStorageRelationsFactory
        //       Use UserStorageRelationsDataService
        User creator = authenticationFacade.getAuthenticatedUser();
        Storage entity = dataService.addWithCreator(
                entitiesFactory.createFrom(creationDto),
                creator
        );
        // TODO: Validation
        return dtoFactory.createFrom(entity);
    }
}