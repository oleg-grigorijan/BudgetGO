package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.CollectionCreationDto;
import com.godev.budgetgo.dto.CollectionInfoDto;
import com.godev.budgetgo.entity.Collection;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategoryKey;
import com.godev.budgetgo.service.data.CollectionsDataService;
import com.godev.budgetgo.service.factory.CollectionDtoFactory;
import com.godev.budgetgo.service.factory.CollectionsFactory;
import com.godev.budgetgo.service.request.CollectionsRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CollectionsRequestServiceImpl implements CollectionsRequestService {

    private final CollectionsDataService dataService;
    private final CollectionsFactory entitiesFactory;
    private final CollectionDtoFactory dtoFactory;
    private final AuthenticationFacade authenticationFacade;

    public CollectionsRequestServiceImpl(
            CollectionsDataService dataService,
            CollectionsFactory entitiesFactory,
            CollectionDtoFactory dtoFactory,
            AuthenticationFacade authenticationFacade
    ) {
        this.dataService = dataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public List<CollectionInfoDto> getAll() {
        return dataService
                .getByUser(authenticationFacade.getAuthenticatedUser())
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Override
    public CollectionInfoDto getByCategoryId(Long categoryId) {
        User user = authenticationFacade.getAuthenticatedUser();
        Collection entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        return dtoFactory.createFrom(entity);
    }

    @Transactional
    @Override
    public CollectionInfoDto create(CollectionCreationDto creationDto) {
        Collection entity = entitiesFactory.createFrom(creationDto);
        Collection savedEntity = dataService.add(entity);
        return dtoFactory.createFrom(savedEntity);
    }

    @Transactional
    @Override
    public void deleteByCategoryId(Long categoryId) {
        User user = authenticationFacade.getAuthenticatedUser();
        Collection entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        dataService.delete(entity);
    }
}
