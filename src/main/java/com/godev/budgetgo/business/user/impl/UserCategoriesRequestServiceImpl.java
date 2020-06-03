package com.godev.budgetgo.business.user.impl;

import com.godev.budgetgo.api.rest.user.dto.UserCategoryCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.business.user.UserCategoriesConverter;
import com.godev.budgetgo.business.user.UserCategoriesDataService;
import com.godev.budgetgo.business.user.UserCategoriesRequestService;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.domain.user.UserCategory;
import com.godev.budgetgo.domain.user.UserCategoryKey;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCategoriesRequestServiceImpl implements UserCategoriesRequestService {

    private final UserCategoriesDataService dataService;

    private final UserCategoriesConverter converter;

    private final AuthenticationFacade authenticationFacade;

    @Transactional(readOnly = true)
    @Override
    public List<UserCategoryInfoDto> getAll() {
        return converter.convertToDtos(dataService.getByUser(authenticationFacade.getAuthenticatedUser()));
    }

    @Transactional(readOnly = true)
    @Override
    public UserCategoryInfoDto getByCategoryId(Long categoryId) {
        User user = authenticationFacade.getAuthenticatedUser();
        UserCategory entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        return converter.convertToDto(entity);
    }

    @Transactional
    @Override
    public UserCategoryInfoDto create(UserCategoryCreationDto creationDto) {
        UserCategory entity = converter.convertToEntity(creationDto);
        UserCategory savedEntity = dataService.add(entity);
        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public UserCategoryInfoDto patchByCategoryId(Long categoryId, UserCategoryPatchesDto patchesDto) {
        User user = authenticationFacade.getAuthenticatedUser();
        UserCategory entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        UserCategory patchedEntity = converter.merge(entity, patchesDto);
        UserCategory savedEntity = dataService.update(patchedEntity);
        return converter.convertToDto(savedEntity);
    }

    @Transactional
    @Override
    public void deleteByCategoryId(Long categoryId) {
        User user = authenticationFacade.getAuthenticatedUser();
        UserCategory entity = dataService.getById(new UserCategoryKey(user.getId(), categoryId));
        dataService.delete(entity);
    }
}
