package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.CollectionCreationDto;
import com.godev.budgetgo.dto.CollectionInfoDto;
import com.godev.budgetgo.entity.Collection;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategoryKey;
import com.godev.budgetgo.service.converter.CollectionsConverter;
import com.godev.budgetgo.service.data.CollectionsDataService;
import com.godev.budgetgo.service.request.CollectionsRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class CollectionsRequestServiceImpl implements CollectionsRequestService {

    private final CollectionsDataService dataService;
    private final CollectionsConverter converter;
    private final AuthenticationFacade authenticationFacade;

    public CollectionsRequestServiceImpl(CollectionsDataService dataService, CollectionsConverter converter, AuthenticationFacade authenticationFacade) {
        this.dataService = dataService;
        this.converter = converter;
        this.authenticationFacade = authenticationFacade;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CollectionInfoDto> getAll() {
        return converter.convertFromEntities(dataService.getByUser(authenticationFacade.getAuthenticatedUser()));
    }

    @Transactional(readOnly = true)
    @Override
    public CollectionInfoDto getByCategoryId(Long categoryId) {
        User user = authenticationFacade.getAuthenticatedUser();
        Collection entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        return converter.convertFromEntity(entity);
    }

    @Transactional
    @Override
    public CollectionInfoDto create(CollectionCreationDto creationDto) {
        Collection entity = converter.convertFromDto(creationDto);
        Collection savedEntity = dataService.add(entity);
        return converter.convertFromEntity(savedEntity);
    }

    @Transactional
    @Override
    public void deleteByCategoryId(Long categoryId) {
        User user = authenticationFacade.getAuthenticatedUser();
        Collection entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        dataService.delete(entity);
    }
}
