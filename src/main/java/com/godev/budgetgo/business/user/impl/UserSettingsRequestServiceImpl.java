package com.godev.budgetgo.business.user.impl;

import com.godev.budgetgo.api.rest.user.dto.UserSettingsInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.business.user.UserSettingsConverter;
import com.godev.budgetgo.business.user.UserSettingsRequestService;
import com.godev.budgetgo.business.user.UsersDataService;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSettingsRequestServiceImpl implements UserSettingsRequestService {

    private final UsersDataService dataService;

    private final UserSettingsConverter converter;

    private final AuthenticationFacade authenticationFacade;

    @Transactional(readOnly = true)
    @Override
    public UserSettingsInfoDto get() {
        User entity = authenticationFacade.getAuthenticatedUser();
        return converter.convertToDto(entity);
    }

    @Transactional
    @Override
    public UserSettingsInfoDto patch(UserSettingsPatchesDto patchesDto) {
        User entity = authenticationFacade.getAuthenticatedUser();
        User patchedEntity = converter.merge(entity, patchesDto);
        User savedEntity = dataService.update(patchedEntity);
        return converter.convertToDto(savedEntity);
    }
}
