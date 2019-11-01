package com.godev.budgetgo.service.request.implementations;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.dto.UserPatchesDto;
import com.godev.budgetgo.entity.User;
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

    public UsersRequestServiceImpl(
            UsersDataService dataService,
            UsersFactory entitiesFactory,
            UserDtoFactory dtoFactory,
            UsersMerger merger
    ) {
        super(dataService, entitiesFactory, dtoFactory, merger);
        this.dataService = dataService;
        this.dtoFactory = dtoFactory;
    }

    @Override
    public UserInfoDto getByLogin(String login) {
        return dtoFactory.createFrom(dataService.getByLogin(login));
    }
}
