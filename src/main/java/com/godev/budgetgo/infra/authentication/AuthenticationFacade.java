package com.godev.budgetgo.infra.authentication;

import com.godev.budgetgo.domain.user.User;

public interface AuthenticationFacade {

    User getAuthenticatedUser();
}
