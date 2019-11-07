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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class UsersRequestServiceImpl implements UsersRequestService {

    private final UsersDataService dataService;
    private final UsersFactory entitiesFactory;
    private final UserDtoFactory dtoFactory;
    private final UsersMerger merger;

    public UsersRequestServiceImpl(
            UsersDataService dataService,
            UsersFactory entitiesFactory,
            UserDtoFactory dtoFactory,
            UsersMerger merger
    ) {
        this.dataService = dataService;
        this.entitiesFactory = entitiesFactory;
        this.dtoFactory = dtoFactory;
        this.merger = merger;
    }

    @Override
    public UserInfoDto getById(Long id) {
        User entity = dataService.getById(id);
        return dtoFactory.createFrom(entity);
    }

    @Override
    public UserInfoDto getByLogin(String login) {
        User entity = dataService.getByLogin(login);
        return dtoFactory.createFrom(entity);
    }

    @Override
    public List<UserInfoDto> getAll() {
        return dataService
                .getAll()
                .stream()
                .map(dtoFactory::createFrom)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserInfoDto create(UserCreationDto creationDto) {
        User entity = entitiesFactory.createFrom(creationDto);
        // TODO: Validation
        User savedEntity = dataService.add(entity);
        return dtoFactory.createFrom(savedEntity);
    }

    // TODO: Move to UserSettingsRequestService
    @Transactional
    @Override
    public UserInfoDto patch(Long id, UserPatchesDto patchesDto) {
        User entity = dataService.getById(id);
        User patchedEntity = merger.merge(patchesDto, entity);
        // TODO: Validation
        User savedEntity = dataService.update(patchedEntity);
        return dtoFactory.createFrom(savedEntity);
    }
}
