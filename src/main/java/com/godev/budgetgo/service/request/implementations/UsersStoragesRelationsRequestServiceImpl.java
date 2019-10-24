package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.UserStorageRelationsCreationDto;
import com.godev.budgetgo.dto.UserStorageRelationsInfoDto;
import com.godev.budgetgo.dto.UserStorageRelationsPatchDto;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.service.data.UsersStoragesRelationsDataService;
import com.godev.budgetgo.service.factory.UserStorageRelationsDtoFactory;
import com.godev.budgetgo.service.factory.UsersStoragesRelationsFactory;
import com.godev.budgetgo.service.merger.UsersStoragesRelationsMerger;
import com.godev.budgetgo.service.request.UsersStoragesRelationsRequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class UsersStoragesRelationsRequestServiceImpl
        extends AbstractRequestService<UserStorageRelations, UserStorageKey, UserStorageRelationsInfoDto, UserStorageRelationsCreationDto, UserStorageRelationsPatchDto>
        implements UsersStoragesRelationsRequestService {

    private final UsersStoragesRelationsDataService dataService;
    private final UserStorageRelationsDtoFactory dtoFactory;

    public UsersStoragesRelationsRequestServiceImpl(
            UsersStoragesRelationsDataService dataService,
            UsersStoragesRelationsFactory entitiesFactory,
            UserStorageRelationsDtoFactory dtoFactory,
            UsersStoragesRelationsMerger merger) {
        super(dataService, entitiesFactory, dtoFactory, merger);
        this.dataService = dataService;
        this.dtoFactory = dtoFactory;
    }

    @Override
    public List<UserStorageRelationsInfoDto> getByStorageId(Long storageId) {
        return dataService
                .getByStorageId(storageId)
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }
}
