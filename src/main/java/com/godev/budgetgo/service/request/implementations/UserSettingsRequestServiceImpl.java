package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.dto.UserSettingsInfoDto;
import com.godev.budgetgo.dto.UserSettingsPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.data.UsersDataService;
import com.godev.budgetgo.service.factory.UserSettingsDtoFactory;
import com.godev.budgetgo.service.merger.UsersSettingsMerger;
import com.godev.budgetgo.service.request.UserSettingsRequestService;
import org.springframework.stereotype.Service;

@Service
class UserSettingsRequestServiceImpl implements UserSettingsRequestService {

    private final UsersDataService dataService;
    private final UserSettingsDtoFactory dtoFactory;
    private final UsersSettingsMerger merger;
    private final AuthenticationFacade authenticationFacade;

    public UserSettingsRequestServiceImpl(
            UsersDataService dataService,
            UserSettingsDtoFactory dtoFactory,
            UsersSettingsMerger merger,
            AuthenticationFacade authenticationFacade
    ) {
        this.dataService = dataService;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public UserSettingsInfoDto get() {
        User entity = authenticationFacade.getAuthenticatedUser();
        return dtoFactory.createFrom(entity);
    }

    @Override
    public UserSettingsInfoDto patch(UserSettingsPatchesDto patchesDto) {
        User entity = authenticationFacade.getAuthenticatedUser();
        User patchedEntity = merger.merge(patchesDto, entity);
        User savedEntity = dataService.update(patchedEntity);
        return dtoFactory.createFrom(savedEntity);
    }
}
