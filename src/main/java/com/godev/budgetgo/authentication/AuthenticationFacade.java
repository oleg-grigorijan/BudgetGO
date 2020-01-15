package com.godev.budgetgo.authentication;

import com.godev.budgetgo.entity.User;

public interface AuthenticationFacade {
    User getAuthenticatedUser();
}
