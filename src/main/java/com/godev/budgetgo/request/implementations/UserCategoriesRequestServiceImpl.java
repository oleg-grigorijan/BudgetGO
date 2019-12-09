package com.godev.budgetgo.request.implementations;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.UserCategoriesConverter;
import com.godev.budgetgo.data.UserCategoriesDataService;
import com.godev.budgetgo.dto.UserCategoryCreationDto;
import com.godev.budgetgo.dto.UserCategoryInfoDto;
import com.godev.budgetgo.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategory;
import com.godev.budgetgo.entity.UserCategoryKey;
import com.godev.budgetgo.request.UserCategoriesRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class UserCategoriesRequestServiceImpl implements UserCategoriesRequestService {

    private final UserCategoriesDataService dataService;
    private final UserCategoriesConverter converter;
    private final AuthenticationFacade authenticationFacade;

    public UserCategoriesRequestServiceImpl(UserCategoriesDataService dataService, UserCategoriesConverter converter, AuthenticationFacade authenticationFacade) {
        this.dataService = dataService;
        this.converter = converter;
        this.authenticationFacade = authenticationFacade;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserCategoryInfoDto> getAll() {
        return converter.convertFromEntities(dataService.getByUser(authenticationFacade.getAuthenticatedUser()));
    }

    @Transactional(readOnly = true)
    @Override
    public UserCategoryInfoDto getByCategoryId(Long categoryId) {
        User user = authenticationFacade.getAuthenticatedUser();
        UserCategory entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        return converter.convertFromEntity(entity);
    }

    @Transactional
    @Override
    public UserCategoryInfoDto create(UserCategoryCreationDto creationDto) {
        UserCategory entity = converter.convertFromDto(creationDto);
        UserCategory savedEntity = dataService.add(entity);
        return converter.convertFromEntity(savedEntity);
    }

    @Transactional
    @Override
    public UserCategoryInfoDto patchByCategoryId(Long categoryId, UserCategoryPatchesDto patchesDto) {
        User user = authenticationFacade.getAuthenticatedUser();
        UserCategory entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        UserCategory patchedEntity = converter.merge(entity, patchesDto);
        UserCategory savedEntity = dataService.update(patchedEntity);
        return converter.convertFromEntity(patchedEntity);
    }

    @Transactional
    @Override
    public void deleteByCategoryId(Long categoryId) {
        User user = authenticationFacade.getAuthenticatedUser();
        UserCategory entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        dataService.delete(entity);
    }
}
