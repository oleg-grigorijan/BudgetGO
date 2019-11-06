package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.dto.UserPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.authorization.UsersAuthorizationService;
import com.godev.budgetgo.service.data.UsersDataService;
import com.godev.budgetgo.service.factory.UserDtoFactory;
import com.godev.budgetgo.service.factory.UsersFactory;
import com.godev.budgetgo.service.merger.UsersMerger;
import com.godev.budgetgo.service.request.UsersRequestService;
import org.springframework.stereotype.Service;

@Service
class UsersRequestServiceImpl
        extends AbstractRequestService<User, Long, UserInfoDto, UserCreationDto, UserPatchesDto>
        implements UsersRequestService {

    private final UsersDataService dataService;
    private final UserDtoFactory dtoFactory;
    private final UsersAuthorizationService authorizationService;

    public UsersRequestServiceImpl(
            UsersDataService dataService,
            UsersFactory entitiesFactory,
            UserDtoFactory dtoFactory,
            UsersMerger merger,
            UsersAuthorizationService authorizationService
    ) {
        super(dataService, entitiesFactory, dtoFactory, merger, authorizationService);
        this.dataService = dataService;
        this.dtoFactory = dtoFactory;
        this.authorizationService = authorizationService;
    }

    @Override
    public UserInfoDto getByLogin(String login) {
        User entity = dataService.getByLogin(login);
        authorizationService.authorizeGet(entity);
        return dtoFactory.createFrom(entity);
    }
}
