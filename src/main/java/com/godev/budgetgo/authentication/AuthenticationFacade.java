package com.godev.budgetgo.authentication;

import com.godev.budgetgo.entity.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();

    User getAuthenticatedUser();
}
