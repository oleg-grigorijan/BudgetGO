package com.godev.budgetgo.authentication;

import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final UsersDataService usersDataService;

    public AuthenticationFacadeImpl(UsersDataService usersDataService) {
        this.usersDataService = usersDataService;
    }

    @Override
    public User getAuthenticatedUser() {
        return usersDataService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
