package com.godev.budgetgo.auth;

import com.godev.budgetgo.entity.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();

    User getAuthenticatedUser();
}
