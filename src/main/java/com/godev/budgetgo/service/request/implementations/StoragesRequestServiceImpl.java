package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.UsersStoragesRelationsDataService;
import com.godev.budgetgo.service.factory.StorageDtoFactory;
import com.godev.budgetgo.service.factory.StoragesFactory;
import com.godev.budgetgo.service.factory.UsersStoragesRelationsFactory;
import com.godev.budgetgo.service.merger.StoragesMerger;
import com.godev.budgetgo.service.request.StoragesRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class StoragesRequestServiceImpl implements StoragesRequestService {

    private final StoragesDataService dataService;
    private final UsersStoragesRelationsDataService relationsDataService;
    private final StoragesFactory entitiesFactory;
    private final StorageDtoFactory dtoFactory;
    private final StoragesMerger merger;
    private final StoragesAuthorizationService authorizationService;
    private final UsersStoragesRelationsFactory relationsFactory;


    public StoragesRequestServiceImpl(
            StoragesDataService dataService,
            UsersStoragesRelationsDataService relationsDataService,
            StoragesFactory entitiesFactory,
            StorageDtoFactory dtoFactory,
            StoragesMerger merger,
            StoragesAuthorizationService authorizationService,
            UsersStoragesRelationsFactory relationsFactory
    ) {
        this.dataService = dataService;
        this.relationsDataService = relationsDataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
        this.authorizationService = authorizationService;
        this.relationsFactory = relationsFactory;
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
        Storage entity = entitiesFactory.createFrom(creationDto);
        // TODO: Validation
        Storage savedEntity = dataService.add(entity);

        UserStorageRelations creatorRelations = relationsFactory.generateCreatorEntityForStorage(savedEntity);
        relationsDataService.add(creatorRelations);

        return dtoFactory.createFrom(savedEntity);
    }
}