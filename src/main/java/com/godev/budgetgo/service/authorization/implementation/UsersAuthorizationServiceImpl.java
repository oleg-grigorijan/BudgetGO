package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserPatchesDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.service.authorization.UsersAuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UsersAuthorizationServiceImpl implements UsersAuthorizationService {
    @Override
    public List<User> getAllAuthorizedEntities() {
        return null;
    }

    @Override
    public void authorizeGet(User entity) {

    }

    @Override
    public void authorizeCreate(UserCreationDto creationDto) {

    }

    @Override
    public void authorizePatch(User entity, UserPatchesDto patchesDto) {

    }
}
