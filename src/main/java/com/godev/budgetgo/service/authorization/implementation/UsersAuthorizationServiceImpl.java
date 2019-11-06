package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.auth.AuthenticationFacade;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.UserAccessDeniedException;
import com.godev.budgetgo.service.authorization.UsersAuthorizationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class UsersAuthorizationServiceImpl implements UsersAuthorizationService {
    private final AuthenticationFacade authenticationFacade;

    public UsersAuthorizationServiceImpl(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public List<User> getAllAuthorizedEntities() {
        return new ArrayList<>();
    }

    @Override
    public void authorizeGet(User entity) {
    }

    @Override
    public void authorizeCreate(User entity) {
    }

    @Override
    public void authorizePatch(User entity, User patchedEntity) {
        if (!entity.getLogin().equals(authenticationFacade.getAuthentication().getName())) {
            throw new UserAccessDeniedException();
        }
    }
}