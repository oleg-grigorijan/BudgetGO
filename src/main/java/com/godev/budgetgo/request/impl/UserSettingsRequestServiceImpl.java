package com.godev.budgetgo.request.impl;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.UserSettingsConverter;
import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.request.UserSettingsRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class UserSettingsRequestServiceImpl implements UserSettingsRequestService {

    private final UsersDataService dataService;
    private final UserSettingsConverter converter;
    private final AuthenticationFacade authenticationFacade;

    public UserSettingsRequestServiceImpl(UsersDataService dataService, UserSettingsConverter converter, AuthenticationFacade authenticationFacade) {
        this.dataService = dataService;
        this.converter = converter;
        this.authenticationFacade = authenticationFacade;
    }

    @Transactional(readOnly = true)
    @Override
    public UserSettingsInfoDto get() {
        User entity = authenticationFacade.getAuthenticatedUser();
        return converter.convertFromEntity(entity);
    }

    @Transactional
    @Override
    public UserSettingsInfoDto patch(UserSettingsPatchesDto patchesDto) {
        User entity = authenticationFacade.getAuthenticatedUser();
        User patchedEntity = converter.merge(entity, patchesDto);
        User savedEntity = dataService.update(patchedEntity);
        return converter.convertFromEntity(savedEntity);
    }
}
