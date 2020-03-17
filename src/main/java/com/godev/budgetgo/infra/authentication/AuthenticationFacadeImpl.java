package com.godev.budgetgo.infra.authentication;

import com.godev.budgetgo.business.user.UsersDataService;
import com.godev.budgetgo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final UsersDataService usersDataService;

    @Override
    public User getAuthenticatedUser() {
        return usersDataService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
